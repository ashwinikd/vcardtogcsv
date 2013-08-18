package com.ashwinikd.vcardtocsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.TelType;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        File contactDir = new File("/path/to/contacts/vcf/files/directory");
        try {
            File[] vcardFiles = contactDir.listFiles();
            List<GoogleContact> contacts = new ArrayList<GoogleContact>();
            VCardEngine engine = new VCardEngine();
            for(File f: vcardFiles) {
                try {
                    System.out.println(f.toString());
                    VCard vcard = engine.parse(f);
                    GoogleContact gc = new GoogleContact();
                    if(vcard.getFN() != null) gc.setName(vcard.getFN().getFormattedName());
                    if(vcard.getN() != null) {
                        gc.setGivenName(vcard.getN().getGivenName());
                        gc.setFamilyName(vcard.getN().getFamilyName());
                    }
                    if(vcard.getEmails() != null)
                        for(EmailType email: vcard.getEmails()) {
                            gc.addEmail("Home", email.getEmail());
                        }
                    if(vcard.getTels() != null)
                        for(TelType num: vcard.getTels()) {
                            gc.addPhone("Mobile", num.getTelephone());
                        }
                    
                    contacts.add(gc);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                } catch (VCardParseException e) {
                    e.printStackTrace();
                }
            }
            List<String[]> lines = new ArrayList<String[]>();
            for(GoogleContact gc: contacts) {
                lines.add(gc.toStringArr());
            }
            File output = new File("/path/to/contacts/vcf/files/directory/out.csv");
            FileWriter writer = new FileWriter(output);
            CSVWriter csvwriter = new CSVWriter(writer);
            csvwriter.writeNext(GoogleContact.getHeader());
            csvwriter.writeAll(lines);
            csvwriter.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
