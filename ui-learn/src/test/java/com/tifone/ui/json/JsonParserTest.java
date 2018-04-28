package com.tifone.ui.json;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tongkao.chen on 2018/4/19.
 */
public class JsonParserTest {
    JsonParser mParser;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("AfterClass");
    }

    @Before
    public void setup() throws Exception {
        System.out.println("Setup");
        mParser = new JsonParser();
    }

    @After
    public void release() throws Exception {
        System.out.println("Release");
        mParser = null;
    }

    @Test
    public void TestToJsonObjectString() throws Exception {
        JsonBean bean = getBeanData();
        // bean to json string
        String jsonResult = mParser.toJsonObjectString(bean);
        assertNotNull(jsonResult);
        System.out.println(jsonResult);

        parserJsonObject(jsonResult);
    }

    @Test
    public void testParserJsonObject() throws Exception {
        String jsonResult = getJsonString();
        parserJsonObject(jsonResult);
    }
    private void parserJsonObject(String json) {
        // json string to bean
        JsonBean resultBean = mParser.parserJsonObject(json);
        assertNotNull(resultBean);
    }
    private String getJsonString() {
        return "{\"summary\":\"Android developer\",\"address\":\"shenzhen\"," +
                "\"distance\":2,\"name\":\"Jack\",\"description\":\"Is a hard worker\"}";
    }
    private String getJsonArrayString() {
        return "[{\"summary\":\"Android developer\",\"address\":\"shenzhen\"," +
                "\"distance\":2,\"name\":\"Jack\",\"description\":\"Is a hard worker\"}," +
                "{\"summary\":\"Android developer2\",\"address\":\"shenzhen2\"," +
                "\"distance\":3,\"name\":\"Jack2\",\"description\":\"Is a hard worker2\"}]";
    }
    private JsonBean getBeanData() {
        return new JsonBean("Jack", "Android developer", "Is a hard worker", "shenzhen" , 2);
    }
    private List<JsonBean> getBeanListData() {
        List<JsonBean> list = new ArrayList<>();
        JsonBean bean1 = new JsonBean("Jack", "Android developer", "Is a hard worker", "shenzhen" , 2);
        JsonBean bean2 = new JsonBean("Jack2", "Android developer2", "Is a hard worker2", "shenzhen2" , 3);
        list.add(bean1);
        list.add(bean2);
        return list;
    }
    private void assertValues(JsonBean bean) {
        assertEquals(bean.getName(), "Jack");
        assertEquals(bean.getSummary(), "Android developer");
        assertEquals(bean.getDescription(), "Is a hard worker");
        assertEquals(bean.getAddress(), "shenzhen");
        assertEquals(bean.getDistance(), 2);
    }

    private void assertArrayValues(List<JsonBean> beans) {
        JsonBean bean1 = beans.get(0);
        JsonBean bean2 = beans.get(1);

        assertNotNull(bean1);
        assertNotNull(bean2);

        assertEquals(bean1.getName(), "Jack");
        assertEquals(bean1.getSummary(), "Android developer");
        assertEquals(bean1.getDescription(), "Is a hard worker");
        assertEquals(bean1.getAddress(), "shenzhen");
        assertEquals(bean1.getDistance(), 2);

        assertEquals(bean2.getName(), "Jack2");
        assertEquals(bean2.getSummary(), "Android developer2");
        assertEquals(bean2.getDescription(), "Is a hard worker2");
        assertEquals(bean2.getAddress(), "shenzhen2");
        assertEquals(bean2.getDistance(), 3);
    }

    @Test
    public void testToJsonArrayString() throws Exception {
        List<JsonBean> beans = getBeanListData();
        String jsonResult = mParser.toJsonArrayString(beans);
        assertNotNull(jsonResult);

        parserJsonArray(jsonResult);
    }

    @Test
    public void testParserJsonArray() throws Exception {
        String json = getJsonArrayString();
        parserJsonArray(json);
    }

    private void parserJsonArray(String json) {
        List<JsonBean> list =  mParser.parserJsonArray(json);
        assertNotNull(list);
        System.out.println(list);
        assertArrayValues(list);
    }

    @Test
    public void testUseGsonToParserObjectString() {
        String json = getJsonString();
        JsonBean bean = mParser.useGsonToParserObjectString(json);
        System.out.println(bean);
        assertNotNull(bean);
        assertValues(bean);
    }

    @Test
    public void testUseGsonToParserArrayString() {
        String json = getJsonArrayString();
        List<JsonBean> beans = mParser.useGsonToParserArrayString(json);
        assertNotNull(beans);
        assertArrayValues(beans);
    }

    @Test
    public void testUseGsonToParserBean() {
        JsonBean bean = getBeanData();
        String json = mParser.useGsonToParserBean(bean);
        JsonBean bean1 = mParser.useGsonToParserObjectString(json);
        assertValues(bean1);
    }

    @Test
    public void testUseGsonToParserArrayBean() {
        List<JsonBean> beans = getBeanListData();
        String json = mParser.useGsonToParserArrayBean(beans);
        List<JsonBean> list = mParser.useGsonToParserArrayString(json);
        assertArrayValues(list);
    }

}