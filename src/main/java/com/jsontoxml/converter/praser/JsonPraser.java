
package com.jsontoxml.converter.praser;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonPraser {
    public void json(String j, String g, String ep) throws IOException {

        /*Latest update 27-01-2023*/

        String packageName = "com.elasticpath.extensions.domain";

        String classContent = "public interface " + g + " {\n" +
                "    //fields, methods, and constructors here\n" +
                "}\n";

        // Create the package directory
        String packageDir = ep + "\\extensions\\core\\ext-core\\src\\main\\java\\com\\elasticpath\\extensions\\domain";
        // Create the class file
        File classFile = new File(packageDir + "\\" + g + ".java");
        try (FileWriter fileWriter = new FileWriter(classFile)) {
            fileWriter.write("package " + packageName + ";\n\n");
            fileWriter.write(classContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




     /*
     import com.google.gson.JsonObject;
     import com.sun.codemodel.JCodeModel;
     import org.json.JSONObject;
     import org.jsonschema2pojo.*;
      import org.jsonschema2pojo.rules.RuleFactory;

     JSONObject jsonObject1 = new JSONObject(new JSONObject(j).getJSONArray("changeSets").get(0).toString());
        JsonObject jsonObject2 = new JsonObject();
        for (int i = 0; i < jsonObject1.getJSONArray("columnValues").length(); i++) {
            if (jsonObject1.getJSONArray("columnValues").getJSONObject(i).get("dataType").toString().equalsIgnoreCase("int")) {
                jsonObject2.addProperty(jsonObject1.getJSONArray("columnValues").getJSONObject(i).get("column").toString(), 123);
            } else {
                jsonObject2.addProperty(jsonObject1.getJSONArray("columnValues").getJSONObject(i).get("column").toString(), "ancdef");
            }
        }
        System.out.println(jsonObject2);
        String d = jsonObject2.toString();

        String packageName = "com.elasticpath.extensions.domain";
        String basePath = "src/main/java";

        File outputPojoDirectory = new File(ep + "\\extensions\\core\\ext-core\\src\\main\\java\\"  + g + ".java");
        outputPojoDirectory.createNewFile();
       // File outputPojoDirectory3 = new File(ep + "\\ extensions\\core\\ext-core\\src\\main\\java\\com\\elasticpath\\extensions\\domain\\impl" + g + ".java");
      //  File outputPojoDirectory2 = new File(ep + "\\extensions\\core\\ext-core\\src\\main\\java\\com\\elasticpath\\extensions\\domain" + g + ".java");

        //File outputPojoDirectory3 = new File(ep + "\\extensions\\core\\ext-core\\src\\main\\java\\com\\elasticpath\\extensions" + g + ".java");
       // outputPojoDirectory.mkdirs();
       // outputPojoDirectory3.createNewFile();
      //boolean p =  outputPojoDirectory3.createNewFile();
       // System.out.println(p);

       // boolean t = outputPojoDirectory2.createNewFile();
       // System.out.println(t);

*//*
        outputPojoDirectory.mkdirs();
*//*
        JCodeModel jcodeModel = new JCodeModel();


        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        Jackson2Annotator jackson2Annotator = new Jackson2Annotator(config);
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, jackson2Annotator, new SchemaStore()), new SchemaGenerator());


        mapper.generate(jcodeModel, g, packageName, d);

        jcodeModel.build(outputPojoDirectory);

    }*/


