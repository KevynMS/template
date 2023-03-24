package com.flakkeeverhuizers.service;

import com.flakkeeverhuizers.controller.model.response.ItemCategoriesResponse;
import com.flakkeeverhuizers.controller.model.response.ItemCategoryResponse;
import com.flakkeeverhuizers.controller.model.response.SpaceResponse;
import com.flakkeeverhuizers.controller.model.response.SpacesResponse;
import com.flakkeeverhuizers.csv.CSVHelper;
import com.flakkeeverhuizers.csv.CategoriesCSV;
import com.flakkeeverhuizers.csv.SpacesCSV;
import com.flakkeeverhuizers.db.entity.ItemCategories;
import com.flakkeeverhuizers.db.entity.Items;
import com.flakkeeverhuizers.db.entity.Spaces;
import com.flakkeeverhuizers.db.repository.ItemCategoriesRepository;
import com.flakkeeverhuizers.db.repository.ItemsRepository;
import com.flakkeeverhuizers.db.repository.SpacesRepository;
import com.flakkeeverhuizers.exception.CSVImportException;
import com.flakkeeverhuizers.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static com.flakkeeverhuizers.csv.CategoriesCSV.*;
import static com.flakkeeverhuizers.csv.SpacesCSV.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class CSVImportService {

    private final ItemCategoriesRepository itemCategoriesRepository;

    private final ItemsRepository itemsRepository;

    private final SpacesRepository spacesRepository;

    public void saveCategoryCSV(MultipartFile file) {
        try {
            if (!CSVHelper.hasCSVFormat(file))
                throw new RuntimeException("The file has an invalid extension");

            List<String> columns = Arrays.asList(CategoriesCSV.values())
                    .stream()
                    .map(column -> column.name()).collect(Collectors.toList());

            List<Map<String, String>> csvRows = CSVHelper.convertCSVToList(file.getInputStream(), columns);
            Map<String, String> icons = getIconsMap();

            csvRows.forEach(csvRow -> {
                log.info("Item to be converted: " + csvRow.get(INVENTORY_ITEM.name()));
                ItemCategories itemCategories = saveItemCategories(csvRow, icons);
                saveItems(csvRow, itemCategories);
            });
        } catch (Exception e) {
            throw new CSVImportException("Fail during CSV category import: " + e.getMessage());
        }
    }

    public void saveSpacesCSV(MultipartFile file) {
        try {
            if (!CSVHelper.hasCSVFormat(file))
                throw new RuntimeException("The file has an invalid extension");

            List<String> columns = Arrays.asList(SpacesCSV.values())
                    .stream()
                    .map(column -> column.name()).collect(Collectors.toList());

            List<Map<String, String>> csvRows = CSVHelper.convertCSVToList(file.getInputStream(), columns);

            csvRows.forEach(csvRow -> {
                log.info("Space converted: " + csvRow.get(SPACE.name()));
                saveSpaces(csvRow);
            });
        } catch (Exception e) {
            throw new CSVImportException("Fail during CSV spaces import: " + e.getMessage());
        }
    }

    public void saveSpaces(Map<String, String> csvRow) {
        Spaces space = new Spaces();
        space.setName(csvRow.get(SPACE.name()));
        spacesRepository.save(space);
    }

    public ItemCategories saveItemCategories(Map<String, String> csvRow, Map<String, String> icons) {
        ItemCategories itemCategories = convertToItemCategories(csvRow, icons);
        if (itemCategories.getId() == null) {
            itemCategories = itemCategoriesRepository.save(itemCategories);
        }
        return itemCategories;
    }

    private ItemCategories convertToItemCategories(Map<String, String> csvRow, Map<String, String> icons) {
        ItemCategories itemCategories = new ItemCategories();
        String categoryName = csvRow.get(CATEGORY_NAME.name());
        Optional<ItemCategories> itemCategoriesOptional = itemCategoriesRepository.findByName(categoryName);

        if (!itemCategoriesOptional.isPresent()) {
            itemCategories.setName(csvRow.get(CATEGORY_NAME.name()));
            itemCategories.setIcon(icons.get(csvRow.get(CATEGORY_NAME.name())));
        } else {
            itemCategories = itemCategoriesOptional.get();
        }

        return itemCategories;
    }

    public void saveItems(Map<String, String> csvRow, ItemCategories itemCategories) {
        Items items = convertToItems(csvRow, itemCategories);
        if (items.getId() == null) {
            itemsRepository.save(items);
        }
    }

    private Items convertToItems(Map<String, String> csvRow, ItemCategories itemCategories) {
        Items items = null;
        Optional<Items> itemsOptional = itemsRepository.
                findByNameAndItemCategories(csvRow.get(INVENTORY_ITEM.name()), itemCategories);

        if (!itemsOptional.isPresent()) {
            items = new Items();

            items.setName(csvRow.get(INVENTORY_ITEM.name()));
            items.setItemCategories(itemCategories);
            items.setNotes(csvRow.get(NOTES.name()));

            BigDecimal volume = csvRow.get(VOLUME.name()) != null
                    ? new BigDecimal(csvRow.get(VOLUME.name()))
                    : new BigDecimal("0");

            items.setVolume(volume);

            if (csvRow.get(TOTAL_ASSEMBLE_TIME.name()) != null) {
                items.setAssemble(Boolean.TRUE);
                items.setDisassemble(Boolean.TRUE);

                Double assembleTime = new Double(csvRow.get(TOTAL_ASSEMBLE_TIME.name())) * 0.6 * 60;
                Double disassembleTime = new Double(csvRow.get(TOTAL_ASSEMBLE_TIME.name())) * 0.4 * 60;

                items.setAssembleTime(assembleTime.intValue());
                items.setDisassembleTime(disassembleTime.intValue());
            }
            if (csvRow.get(TOTAL_PICK_TIME.name()) != null) {
                items.setPickUp(Boolean.TRUE);
                items.setHangUp(Boolean.TRUE);

                Double pickUpTime = new Double(csvRow.get(TOTAL_PICK_TIME.name())) * 0.4 * 60;
                Double hangUpTime = new Double(csvRow.get(TOTAL_PICK_TIME.name())) * 0.6 * 60;

                items.setPickUpTime(pickUpTime.intValue());
                items.setHangUpTime(hangUpTime.intValue());
            }
        } else {
            items = itemsOptional.get();
        }

        return items;
    }

    public ItemCategoriesResponse findAllCategories(String sortedBy, String orderBy) {
        ItemCategoriesResponse itemCategoriesResponse = new ItemCategoriesResponse();
        List<ItemCategoryResponse> itemCategoriesResponseList = new ArrayList<>();

        itemsRepository.findAll(getSort(sortedBy, orderBy)).forEach(items -> {
            ItemCategoryResponse itemCategoryResponse = new ItemCategoryResponse();
            itemCategoryResponse.setId(items.getId().toString());
            itemCategoryResponse.setItemCategoryId(items.getId().toString());
            itemCategoryResponse.setIcon(items.getItemCategories().getIcon());
            itemCategoryResponse.setName(items.getName());
            itemCategoryResponse.setCustom(items.getCustom());

            itemCategoriesResponseList.add(itemCategoryResponse);
        });

        if (itemCategoriesResponseList.isEmpty())
            throw new RecordNotFoundException("ItemCategory not found");

        itemCategoriesResponse.setItemCategories(itemCategoriesResponseList);

        return itemCategoriesResponse;
    }

    public SpacesResponse findAllSpaces(String sortedBy, String orderBy) {
        SpacesResponse spacesResponse = new SpacesResponse();
        List<SpaceResponse> spaceResponseList = new ArrayList<>();

        spacesRepository.findAll(getSort(sortedBy, orderBy)).forEach(spaces -> {
            SpaceResponse spaceResponse = new SpaceResponse();
            spaceResponse.setSpaceId(spaces.getId().toString());
            spaceResponse.setId(spaces.getId().toString());
            spaceResponse.setName(spaces.getName());
            spaceResponse.setCustom(spaces.getCustom());

            spaceResponseList.add(spaceResponse);
        });

        if (spaceResponseList.isEmpty())
            throw new RecordNotFoundException("Space(s) not found");

        spacesResponse.setSpaces(spaceResponseList);

        return spacesResponse;
    }

    private Sort getSort(String sortedBy, String orderBy) {
        return orderBy.equals("desc")
                ? Sort.by(Sort.Direction.DESC, sortedBy)
                : Sort.by(Sort.Direction.ASC, sortedBy);
    }

    private Map<String, String> getIconsMap() {
        Map<String, String> icons = new HashMap<>();
        icons.put("Kasten", "cupboard.svg");
        icons.put("Kledingkasten", "cupboard.svg");
        icons.put("Kasten bijzonder", "cupboard.svg");
        icons.put("Banken", "couch.svg");
        icons.put("Opbergen", "storage.svg");
        icons.put("Stoelen", "chair.svg");
        icons.put("Fauteuils", "chair.svg");
        icons.put("Verlichting", "lamp.svg");
        icons.put("Tafels", "table.svg");

        return icons;
    }
}
