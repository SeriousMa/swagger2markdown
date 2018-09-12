/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.serious.s2m;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.serious.s2m.model.DocTag;
import org.serious.s2m.model.Field;
import org.serious.s2m.model.HttpApi;
import org.serious.s2m.model.ResponseModel;

import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.properties.Property;
import io.swagger.parser.SwaggerParser;

/**
 * @author : maduo
 * @date : 2018/9/11 下午8:01
 */
public class Main {

    private static final File outputDirectory = new File("/Users/maduo/Desktop");
    private static final String swaggerJsonUrl = "http://localhost:8050/v2/api-docs";

    public static void main(String[] args) {
        prepareVelocityEngine();
        Swagger swagger = new SwaggerParser().read(swaggerJsonUrl);
        System.out.println(swagger);
        List<DocTag> docTagList = new ArrayList<DocTag>();
        for (Tag tag : swagger.getTags()) {
            DocTag docTag = new DocTag();
            docTag.setName(tag.getName());
            docTag.setDescription(tag.getDescription());
            List<HttpApi> httpApiList = new ArrayList<HttpApi>();
            for (String key : swagger.getPaths().keySet()) {
                HttpApi httpApi = new HttpApi();
                httpApi.setPath(key);
                Path path = swagger.getPath(key);
                if (path.getGet() != null && path.getGet().getTags().contains(tag.getName())) {
                    httpApi.setRequestMethod("GET");
                    httpApi.setSummary(path.getGet().getSummary());
                    httpApi.setDescription(path.getGet().getDescription());
                    httpApi.setParameterList(path.getGet().getParameters());
                    httpApiList.add(httpApi);
                }
                if (path.getPost() != null && path.getPost().getTags().contains(tag.getName())) {
                    httpApi.setRequestMethod("POST");
                    httpApi.setSummary(path.getPost().getSummary());
                    httpApi.setDescription(path.getPost().getDescription());
                    httpApi.setParameterList(path.getPost().getParameters());
                    httpApiList.add(httpApi);
                }

                if (path.getDelete() != null && path.getDelete().getTags().contains(tag.getName())) {
                    httpApi.setRequestMethod("DELETE");
                    httpApi.setSummary(path.getDelete().getSummary());
                    httpApi.setDescription(path.getDelete().getDescription());
                    httpApi.setParameterList(path.getDelete().getParameters());
                    httpApiList.add(httpApi);
                }
                if (path.getHead() != null && path.getHead().getTags().contains(tag.getName())) {
                    httpApi.setRequestMethod("HEAD");
                    httpApi.setSummary(path.getHead().getSummary());
                    httpApi.setDescription(path.getHead().getDescription());
                    httpApi.setParameterList(path.getHead().getParameters());
                    httpApiList.add(httpApi);
                }

            }
            docTag.setHttpApiList(httpApiList);
            docTagList.add(docTag);
        }

        for (DocTag docTag : docTagList) {
            createApiDoc(docTag);
        }

    }

    private static void createApiDoc(DocTag docTag) {
        try {
            Template template = Velocity.getTemplate("api_doc.vm", "UTF-8");
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("H1", "#");
            velocityContext.put("H2", "##");
            velocityContext.put("H3", "###");
            velocityContext.put("H4", "####");
            velocityContext.put("H5", "#####");
            velocityContext.put("docTag", docTag);
            String fileName = String.format("%s.md", docTag.getName());

            File folder = new File(outputDirectory.getPath() + "/" + "api-doc/");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, fileName)));

            if (template != null) {
                template.merge(velocityContext, writer);
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("create api doc error", e);
        }
    }

    private static void prepareVelocityEngine() {
        Properties p = new Properties();

        p.setProperty(VelocityEngine.VM_CONTEXT_LOCALSCOPE, Boolean.toString(true));
        p.setProperty(VelocityEngine.RESOURCE_LOADER, "classpath");
        p.setProperty("classpath."
                + VelocityEngine.RESOURCE_LOADER
                + ".class", ClasspathResourceLoader.class.getName());

        Velocity.init(p);
    }

    private static Map<String, ResponseModel> parseDefinition(Map<String, Model> definitionMaps) {
        Map<String, ResponseModel> result = new HashMap<String, ResponseModel>();
        for (String key : definitionMaps.keySet()) {
            Model model = definitionMaps.get(key);
            //            model.getProperties()
        }
        return null;
    }

    private static ResponseModel parseProperty(Map<String, Model> definitionMaps, Model model) {
        Map<String, Field> map = new HashMap<String, Field>();
        for (String key : model.getProperties().keySet()) {
            Property property = model.getProperties().get(key);
            Field field = new Field();
            field.setTitle(property.getTitle());
            field.setDescription(property.getDescription());
            field.setType(property.getType());
        }
        return null;

    }
}



