package com.ashwinikd.vcardtocsv;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.NicknameType;
import net.sourceforge.cardme.vcard.types.TelType;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String inputDir = null;
    private static String outputFile = null;
    
    public static void main( String[] args )
    {
        if (processOpts(args)) {
            return;
        }
        
    }
    
    private static boolean processOpts(String[] args) {
        inputDir = null;
        outputFile = null;
        
        LongOpt[] longOpts = new LongOpt[4];
        longOpts[0] = new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h');
        longOpts[1] = new LongOpt("convert", LongOpt.NO_ARGUMENT, null, 'c');
        longOpts[2] = new LongOpt("input", LongOpt.REQUIRED_ARGUMENT, null, 'i');
        longOpts[3] = new LongOpt("output", LongOpt.REQUIRED_ARGUMENT, null, 'o');
        Getopt g = new Getopt("VCard to Google Contacts CSV", args, "hci:o:", longOpts);
        int c;
        boolean convert = false;
        while ((c = g.getopt()) != -1) {
            switch (c) {
                case 'h': help();
                    return true;
                case 'c': convert = true;
                    System.out.println("converting");
                    break;
                case 'i': inputDir = g.getOptarg();
                    break;
                case 'o': outputFile = g.getOptarg();
                    break;
                default:
                    System.out.println("Nothing to do");
            }
        }
        
        if(convert) {
            if(inputDir == null) {
                System.out.println("Please provide an input dir using -i");
                return true;
            }
            
            if(outputFile == null || outputFile.trim().equals("")) {
                outputFile = inputDir + "/vcard2g.csv";
            }
            
            convert();
        }
        
        return true;
    }
    
    private static void help() {
        System.out.println("VCard2GCSV (Converts vcf files to csv compatible with google contacts.)");
        System.out.println("USAGE:");
        System.out.println("  vcard2gcsv -c -i /path/to/input/csv/dir [-o /path/to/output/file.csv]");
        System.out.println("COMMANDS:");
        System.out.println("  --convert [-c]");
        System.out.println("    convert the files to csv");
        System.out.println("  --input [-i]");
        System.out.println("    (required) input directory");
        System.out.println("  --output [-o]");
        System.out.println("    (optional) output csv file path. If not given then results are written to vcard2g.csv in input directory.");
    }
    
    private static void convert() {
        if(inputDir == null) {
            System.out.println("Input directory cannot be empty!!!");
            return;
        }
        
        System.out.println("Input directory is " + inputDir);
        System.out.println("Output File is " + outputFile);
        
        File contactDir = new File(inputDir);
        
        try {
            File[] vcardFiles = contactDir.listFiles();
            List<GoogleContact> contacts = new ArrayList<GoogleContact>();
            VCardEngine engine = new VCardEngine();
            for(File f: vcardFiles) {
                try {
                    VCard vcard = engine.parse(f);
                    GoogleContact gc = new GoogleContact();
                    if(vcard.getFN() != null) gc.setName(vcard.getFN().getFormattedName());
                    
                    if(vcard.getN() != null) {
                        gc.setGivenName(vcard.getN().getGivenName());
                        gc.setFamilyName(vcard.getN().getFamilyName());
                        
                        List<String> prefixes = vcard.getN().getHonorificPrefixes();
                        if(prefixes != null) {
                            for(String prefix: prefixes) {
                                gc.setNamePrefix(prefix);
                                break;
                            }
                        }
                        
                        List<String> suffixes = vcard.getN().getHonorificSuffixes();
                        if(prefixes != null) {
                            for(String suffix: suffixes) {
                                gc.setNameSuffix(suffix);
                                break;
                            }
                        }
                    }
                    
                    if(vcard.getNicknames() != null) {
                        NicknameType nicks = vcard.getNicknames();
                        if(nicks.getNicknames() != null) {
                            for(String nick: nicks.getNicknames()) {
                                if(nick != null && ! nick.trim().equals("")) {
                                    gc.setNickname(nick);
                                    break;
                                }
                            }
                        }
                    }
                    
                    if(vcard.getBDay() != null) {
                        Calendar cal = vcard.getBDay().getBirthday();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        gc.setBirthday(format.format(cal.getTime()));
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
                } catch (VCardParseException e) {
                    System.out.println("Could not parse file " + f.toString());
                }
            }
            
            List<String[]> lines = new ArrayList<String[]>();
            for(GoogleContact gc: contacts) {
                lines.add(gc.toStringArr());
            }
            
            File output = new File(outputFile);
            FileWriter writer = new FileWriter(output);
            CSVWriter csvwriter = new CSVWriter(writer);
            csvwriter.writeNext(GoogleContact.getHeader());
            csvwriter.writeAll(lines);
            csvwriter.close();
        } catch (SecurityException e1) {
            e1.printStackTrace();
            System.out.println("Could not read input files. Please check the permissions on the directory: " + inputDir);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not write output to file " + outputFile);
        }
    }
}
