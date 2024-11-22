package com.java.examples.stackoverflow.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    public static void main(String[] args) {



        String xmlString = """
                <awd>
                    <crminfo>
                        <UserProfile>
                            <FirstName>Direct Lens 01</FirstName>
                            <MiddleInit />
                            <LastName>automation test</LastName>
                            <CompanyName />
                            <Email>di@gmail.com</Email>
                            <Phone>123456789</Phone>
                            <Address1>123 hello st</Address1>
                            <Address2>unit 4439</Address2>
                            <City>Toronto</City>
                            <State>ON</State>
                            <ZipCode>M5H 2R2</ZipCode>
                            <Country>CAN</Country>
                            <Province />
                            <Zip>M5H 2R2</Zip>
                            <ProfessionalDesignation />
                            <Website />
                            <BusinessRegistrationNo />
                            <AfslNo />
                            <AuthorisedRepNo />
                            <Custodian />
                            <TransferAgent />
                            <Address3 />
                            <JobTitle />
                            <BusinessUnit />
                            <FirmType>Academic/Library</FirmType>
                            <JobFunction>Fund Analysis</JobFunction>
                        </UserProfile>
                    </crminfo>
                </awd>
                            """;


        List<Map<String, String>> userProfile = parsedXMldata(xmlString, "UserProfile");
        userProfile.stream().forEach(System.out::println);
    }

    public static List<Map<String,String>> parsedXMldata(String xml, String tagName) {

        List<Map<String, String>> result;
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();

            Document sourceDoc = db.parse(new InputSource(new StringReader(xml)));

            sourceDoc.getDocumentElement().normalize();

            NodeList UserProfile = sourceDoc.getElementsByTagName("UserProfile");

            result = new ArrayList<>();
            for (int x = 0, size = UserProfile.getLength(); x < size; x++) {

                //for each user profile print all their nodes

                NodeList profile = UserProfile.item(x).getChildNodes();

                System.out.println();
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 0; i < profile.getLength(); i++) {
                    if (profile.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        //System.out.println(profile.item(i).getNodeName() + " : " + profile.item(i).getTextContent());
                        map.put(profile.item(i).getNodeName(), profile.item(i).getTextContent());
                    }
                }
                result.add(map);
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
