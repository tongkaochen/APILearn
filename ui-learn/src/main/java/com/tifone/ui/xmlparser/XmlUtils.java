package com.tifone.ui.xmlparser;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by tongkao.chen on 2018/4/24.
 */

public class XmlUtils {

    public List<XmlBean> parseXmlWithDom(InputStream inputStream) {
        List<XmlBean> list = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //InputStream inputStream = context.getAssets().open("sample.xml");

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String name = element.getAttribute("name");
                String type = element.getAttribute("type");
                int value = Integer.valueOf(element.getAttribute("value"));
                XmlBean bean = new XmlBean(name, type, value);
                list.add(bean);

                NodeList subNode = element.getElementsByTagName("item");
                NodeList childNodes = element.getElementsByTagName("bean11");
                ///
                for (int y = 0; childNodes != null && y < childNodes.getLength(); y++) {
                    Element childElement = (Element) childNodes.item(y);
                    Log.e("tifone", "childList: " + childNodes.getLength());
                    NodeList bean11List = childElement.getChildNodes();
                    for (int z = 0; z < bean11List.getLength(); z++) {
                        Node child = bean11List.item(z);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            Log.e("tifone", "getNodeName: " + child.getNodeName());
                            if ("name".equals(child.getNodeName())) {
                                name = child.getFirstChild().getNodeValue();
                                Log.e("tifone", "parseXmlWithDom: " + name);
                            }
                            if ("type".equals(child.getNodeName())) {
                                type = child.getFirstChild().getNodeValue();
                            }
                            if ("value".equals(child.getNodeName())) {
                                value = Integer.valueOf(child.getFirstChild().getNodeValue());
                            }
                        }
                    }
                    bean = new XmlBean(name, type, value);
                    list.add(bean);
                }

                for (int z = 0; subNode != null && z < subNode.getLength(); z++) {
                    Element childElement = (Element) subNode.item(z);
                    name = childElement.getAttribute("name");
                    type = childElement.getAttribute("type");
                    value = Integer.valueOf(childElement.getAttribute("value"));
                    bean = new XmlBean(name, type, value);
                    list.add(bean);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<XmlBean> parseXmlWithPull(InputStream inputStream) {
        List<XmlBean> list = new ArrayList<>();
        XmlBean bean = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(inputStream, "UTF-8");
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                //Log.e("tifone", "depth: " + xmlPullParser.getDepth());
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.e("tifone", "START_DOCUMENT: " + xmlPullParser.getName());
                        bean = new XmlBean();
                        break;
                    case XmlPullParser.START_TAG:
                        Log.e("tifone", "START_TAG: " + xmlPullParser.getName());

                        if ("bean".equals(xmlPullParser.getName())) {
                            bean = new XmlBean();
                            Log.e("tifone", "START_DOCUMENT name: " + xmlPullParser.getAttributeValue(null, "name"));
                            Log.e("tifone", "START_DOCUMENT type: " + xmlPullParser.getAttributeValue(null, "type"));
                            Log.e("tifone", "START_DOCUMENT value: " + xmlPullParser.getAttributeValue(null, "value"));
                            bean.setName(xmlPullParser.getAttributeValue(null, "name"));
                            bean.setType(xmlPullParser.getAttributeValue(null, "type"));
                            bean.setValue(Integer.valueOf(xmlPullParser.getAttributeValue(null, "value")));
                            list.add(bean);
                            bean = null;
                        }
                        if ("bean11".equals(xmlPullParser.getName())) {
                            bean = new XmlBean();
                        }
                        if ("item".equals(xmlPullParser.getName())) {
                            bean = new XmlBean();
                            bean.setName(xmlPullParser.getAttributeValue(null, "name"));
                            bean.setType(xmlPullParser.getAttributeValue(null, "type"));
                            bean.setValue(Integer.valueOf(xmlPullParser.getAttributeValue(null, "value")));
                        }
                        if ("name".equals(xmlPullParser.getName())) {
                            //Log.e("tifone", "name = " + xmlPullParser.nextText());
                            bean.setName(xmlPullParser.nextText());
                        }
                        if ("type".equals(xmlPullParser.getName())) {
                            //Log.e("tifone", "type = " + xmlPullParser.nextText());
                            bean.setType(xmlPullParser.nextText());
                        }
                        if ("value".equals(xmlPullParser.getName())) {
                            //Log.e("tifone", "value = " + xmlPullParser.nextText());
                            bean.setValue(Integer.valueOf(xmlPullParser.nextText()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.e("tifone", "END_TAG: " + xmlPullParser.getName());
                        if ("bean".equals(xmlPullParser.getName())) {
                        }
                        if ("bean11".equals(xmlPullParser.getName()) || "bean".equals(xmlPullParser.getName())
                                || "item".equals(xmlPullParser.getName())) {
                            if (bean != null) {
                                list.add(bean);
                                bean = null;
                            }
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        Log.e("tifone", "END_DOCUMENT: " + xmlPullParser.getName());
                        break;
                }
                event = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return list;
    }
}
