package com.jsontoxml.converter.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jsontoxml.converter.model.*;
import com.jsontoxml.converter.newModel.*;
import com.jsontoxml.converter.praser.JsonPraser;
import com.jsontoxml.converter.utils.ProcessHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
@RequestMapping("api/you/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {
    String g = "";

    @PostMapping(value = "/newConverter")
    public ResponseEntity<ResponseAsList> create(
            @RequestParam(name = "epPath") String epPath, @RequestParam(name = "projectName") String projectName,
            @RequestBody RequestDto requestDto) throws IOException {
        ResponseAsList responseAsList = new ResponseAsList();


        List<ChangeSet> sets = new ArrayList<ChangeSet>();

        responseAsList.setXmlns("http://www.liquibase.org/xml/ns/dbchangelog");
        responseAsList.setXmlnsxsi("http://www.w3.org/2001/XMLSchema-instance");
        responseAsList.setSchemaLocation("http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd");
        responseAsList.setLogicalFilePath("com.elasticpath.extensions/db/ext-database/schema-customizations-changelog.xml");

        for (int p = 0; p < requestDto.getLiquibaseRequestDTOList().size(); p++) {
            ChangeSet set = new ChangeSet();
            List<CreateTableItem> createTableItems = new ArrayList<CreateTableItem>();
            List<InsertItem> insertItems = new ArrayList<InsertItem>();
            List<AddColumnItem> addColumns = new ArrayList<AddColumnItem>();
            List<AddForeignKeyConstraintItem> addForeignKeyConstraintItems = new ArrayList<AddForeignKeyConstraintItem>();
            List<UpdateItem> updateItems = new ArrayList<UpdateItem>();

            LiquibaseRequestDTO liquibaseRequestDTO = requestDto.getLiquibaseRequestDTOList().get(p);
            for (int i = 0; i < liquibaseRequestDTO.getChangeSets().size(); i++) {

                int y = liquibaseRequestDTO.getChangeSets().size();
                System.out.println(y);

                ChangeSetsItem changeSetsItem = liquibaseRequestDTO.getChangeSets().get(i);

                switch (changeSetsItem.getAction()) {

                    case "create.table": {

                        List<ColumnItem> columnItemList = new ArrayList<ColumnItem>();

                        for (int j = 0; j < changeSetsItem.getColumnValues().size(); j++) {
                            ColumnValuesItem columnValuesItem = changeSetsItem.getColumnValues().get(j);

                            ColumnItem columnItem = new ColumnItem();
                            columnItem.setName(columnValuesItem.getColumn());
                            columnItem.setType(columnValuesItem.getDataType());

                            Constraints constraints = new Constraints();
                            if (columnValuesItem.isNn()) {
                                constraints.setNullable("true");
                            } else {
                                constraints.setNullable("false");
                            }
                            if (columnValuesItem.isPk()) {
                                constraints.setPrimaryKey("true");
                            } else {
                                constraints.setPrimaryKey("false");
                            }
                            columnItem.setConstraints(constraints);
                            columnItemList.add(columnItem);
                        }
                        CreateTableItem createTableItem = new CreateTableItem();
                        createTableItem.setTableName(changeSetsItem.getCoreTableName());
                        g = changeSetsItem.getCoreTableName();
                        createTableItem.setColumn(columnItemList);
                        createTableItems.add(createTableItem);

                    }

                    case "insert.table": {

                        List<ColumnItem> columnItemList = new ArrayList<ColumnItem>();
                        try {

                            for (int k = 0; k < changeSetsItem.getInsertValues().size(); k++) {

                                InsertValuesItem insertValuesItem = changeSetsItem.getInsertValues().get(k);

                                ColumnItem columnItem = new ColumnItem();
                                columnItem.setName(insertValuesItem.getColumn());
                                columnItem.setValue(insertValuesItem.getValue());
                                columnItem.setValueNumeric(insertValuesItem.getValueNumeric());
                                columnItemList.add(columnItem);
                            }
                            InsertItem insertItem = new InsertItem();
                            insertItem.setColumn(columnItemList);
                            insertItem.setTableName(changeSetsItem.getCoreTableName());
                            insertItems.add(insertItem);

                        } catch (NullPointerException e) {

                        }

                    }


                    case "extend.table": {

                        List<ColumnItem> columnItemList = new ArrayList<ColumnItem>();

                        try {
                            for (int l = 0; l < changeSetsItem.getAddValues().size(); l++) {
                                AddValues addValues = changeSetsItem.getAddValues().get(l);

                                ColumnItem columnItem = new ColumnItem();
                                columnItem.setName(addValues.getColumn());
                                columnItem.setType(addValues.getDataType());
                                columnItemList.add(columnItem);
                            }

                            AddColumnItem addColumnItem = new AddColumnItem();
                            addColumnItem.setColumn(columnItemList);
                            addColumnItem.setTableName(changeSetsItem.getCoreTableName());
                            addColumns.add(addColumnItem);


                        } catch (NullPointerException ex) {

                        }
                    }


                    case "index.foreign": {

                        try {
                            List<ForeignKeyConstraintItem> foreignKeyConstraintItem1 = changeSetsItem.getForgeinKeyConstraint();

                            for (int k = 0; k < foreignKeyConstraintItem1.size(); k++) {
                                ForeignKeyConstraintItem foreignKeyConstraintItem2 = foreignKeyConstraintItem1.get(k);

                                AddForeignKeyConstraintItem addForeignKeyConstraint = new AddForeignKeyConstraintItem();

                                addForeignKeyConstraint.setBaseTableName(foreignKeyConstraintItem2.getBaseTableName());
                                addForeignKeyConstraint.setBaseColumnNames(foreignKeyConstraintItem2.getBaseColumnNames());
                                addForeignKeyConstraint.setConstraintName(foreignKeyConstraintItem2.getConstraintName());
                                addForeignKeyConstraint.setReferencedTableName(foreignKeyConstraintItem2.getReferencedTableName());
                                addForeignKeyConstraint.setReferencedColumnNames(foreignKeyConstraintItem2.getReferencedColumnNames());

                                addForeignKeyConstraintItems.add(addForeignKeyConstraint);
                            }

                        } catch (NullPointerException ex) {

                        }

                    }

                    case "update.table": {
                        List<ColumnItem> columnItemList = new ArrayList<ColumnItem>();

                        try {
                            for (int m = 0; m < changeSetsItem.getUpdateValues().size(); m++) {
                                UpdateValues updateValues = changeSetsItem.getUpdateValues().get(m);

                                ColumnItem columnItem = new ColumnItem();
                                columnItem.setName(updateValues.getColumn());
                                columnItem.setType(updateValues.getDataType());
                                columnItemList.add(columnItem);
                            }
                            UpdateItem updateItem = new UpdateItem();
                            updateItem.setColumn(columnItemList);
                            updateItem.setTableName(changeSetsItem.getCoreTableName());
                            updateItems.add(updateItem);
                        } catch (NullPointerException ex) {

                        }
                    }

                }


            }
            set.setId(String.valueOf(liquibaseRequestDTO.getId()));
            set.setAuthor(liquibaseRequestDTO.getAuthor());
            set.setCreateTable(createTableItems);
            set.setInsert(insertItems);
            set.setAddColumn(addColumns);
            set.setAddForeignKeyConstraint(addForeignKeyConstraintItems);
            set.setUpdateItems(updateItems);
            sets.add(set);
            responseAsList.setChangeSet(sets);




            ObjectMapper objectMapper = JsonMapper.builder().disable(MapperFeature.USE_ANNOTATIONS).build();
            JsonPraser jsonPraser = new JsonPraser();
            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(requestDto);
            System.out.println(json);
            g = g.substring(1);
            System.out.println(g);
            g = g.substring(0, 1).toUpperCase() + g.substring(1).toLowerCase();
            jsonPraser.json(json, g, epPath);

        }
        StringWriter sw = new StringWriter();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ResponseAsList.class);
            Marshaller jaxbMarsheller = jaxbContext.createMarshaller();
            jaxbMarsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarsheller.marshal(responseAsList, sw);
            String XmlString = sw.toString();
            System.out.println(XmlString);
            String changeLogLocationEndPoint = epPath + "\\extensions\\database\\ext-data\\src\\main\\resources\\schema\\" + projectName + "-changelog.xml";
            System.out.println(changeLogLocationEndPoint);
            FileWriter f = new FileWriter(changeLogLocationEndPoint);
            f.write(XmlString);
            f.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            File xmlFile = new File(epPath + "\\extensions\\database\\ext-data\\src\\main\\resources\\schema\\schema-initializer-changelog.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            Element documentElement = document.getDocumentElement();
            Element nodeElement = document.createElement("include");
            nodeElement.setAttribute("file", projectName + "-changelog.xml");
            nodeElement.setAttribute("relativeToChangelogFile", "true");
            documentElement.appendChild(nodeElement);

            document.replaceChild(documentElement, documentElement);
            Transformer tFormer = TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
            tFormer.transform(source, result);

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            String cmd = "cd " + epPath + "\\extensions\\database\\; mvn clean install -Pupdate-db";

            new ProcessHelper().exec(cmd);
        }

        return new ResponseEntity<ResponseAsList>(responseAsList,HttpStatus.CREATED);

    }

    @GetMapping("/listProjects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Object getAll() throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        LiquibaseRequestDTO example = objectMapper.readValue(new File("D:\\Epdatabaseplugin\\dev10\\src\\main\\java\\com\\jsontoxml\\converter\\json\\sample.json"), LiquibaseRequestDTO.class);
        return ResponseEntity.status(200).body(example);
    }

}


