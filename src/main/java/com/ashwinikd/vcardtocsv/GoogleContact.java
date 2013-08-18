package com.ashwinikd.vcardtocsv;

import java.util.ArrayList;
import java.util.List;

public class GoogleContact
{
    private String Name = "";
    private String GivenName = "";
    private String AdditionalName = "";
    private String FamilyName = "";
    private String YomiName = "";
    private String GivenNameYomi = "";
    private String AdditionalNameYomi = "";
    private String FamilyNameYomi = "";
    private String NamePrefix = "";
    private String NameSuffix = "";
    private String Initials = "";
    private String Nickname = "";
    private String ShortName = "";
    private String MaidenName = "";
    private String Birthday = "";
    private String Gender = "";
    private String Location = "";
    private String BillingInformation = "";
    private String DirectoryServer = "";
    private String Mileage = "";
    private String Occupation = "";
    private String Hobby = "";
    private String Sensitivity = "";
    private String Priority = "";
    private String Subject = "";
    private String Notes = "";
    private String GroupMembership = "";
    private List<Email> emails = new ArrayList<GoogleContact.Email>();
    private List<Phone> phones = new ArrayList<GoogleContact.Phone>();
    private List<Organization> orgs = new ArrayList<GoogleContact.Organization>();
    
    private static int numEmails = 0;
    private static int numPhones = 0;
    private static int numOrgs   = 0;

    public void setName(String name) {
        Name = name;
    }

    public void setGivenName(String givenName) {
        GivenName = givenName;
    }

    public void setAdditionalName(String additionalName) {
        AdditionalName = additionalName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public void setYomiName(String yomiName) {
        YomiName = yomiName;
    }

    public void setGivenNameYomi(String givenNameYomi) {
        GivenNameYomi = givenNameYomi;
    }

    public void setAdditionalNameYomi(String additionalNameYomi) {
        AdditionalNameYomi = additionalNameYomi;
    }

    public void setFamilyNameYomi(String familyNameYomi) {
        FamilyNameYomi = familyNameYomi;
    }

    public void setNamePrefix(String namePrefix) {
        NamePrefix = namePrefix;
    }

    public void setNameSuffix(String nameSuffix) {
        NameSuffix = nameSuffix;
    }

    public void setInitials(String initials) {
        Initials = initials;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public void setMaidenName(String maidenName) {
        MaidenName = maidenName;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setBillingInformation(String billingInformation) {
        BillingInformation = billingInformation;
    }

    public void setDirectoryServer(String directoryServer) {
        DirectoryServer = directoryServer;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public void setSensitivity(String sensitivity) {
        Sensitivity = sensitivity;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public void setGroupMembership(String groupMembership) {
        GroupMembership = groupMembership;
    }

    public void addEmail(String emailType, String emailValue) {
        Email email = new Email();
        email.type = emailType;
        email.value = emailValue;
        emails.add(email);
        if(emails.size() > numEmails) {
            numEmails = emails.size();
        }
    }

    public void addPhone(String phoneType, String phoneValue) {
        Phone phone = new Phone();
        phone.type = phoneType;
        phone.value = phoneValue;
        phones.add(phone);
        if(phones.size() > numPhones) {
            numPhones = phones.size();
        }
    }

    public void setOrgType(
        String orgType,
        String orgName,
        String orgYomiName,
        String orgTitle,
        String orgDept,
        String orgSymbol,
        String orgLocation,
        String orgJobDesc
    ) {
        Organization org = new Organization();
        org.Type = orgType;
        org.Name = orgName;
        org.YomiName = orgYomiName;
        org.Title = orgTitle;
        org.Department = orgDept;
        org.Symbol = orgSymbol;
        org.Location = orgLocation;
        org.JobDescription = orgJobDesc;
        orgs.add(org);
        if(orgs.size() > numOrgs) {
            numOrgs = orgs.size();
        }
    }
    
    public String[] toStringArr() {
        List<String> vals = new ArrayList<String>();
        
        vals.add((Name == null) ? "" : Name);
        vals.add((GivenName == null) ? "" : GivenName);
        vals.add((AdditionalName == null) ? "" : AdditionalName);
        vals.add((FamilyName == null) ? "" : FamilyName);
        vals.add((YomiName == null) ? "" : YomiName);
        vals.add((GivenNameYomi == null) ? "" : GivenNameYomi);
        vals.add((AdditionalNameYomi == null) ? "" : AdditionalNameYomi);
        vals.add((FamilyNameYomi == null) ? "" : FamilyNameYomi);
        vals.add((NamePrefix == null) ? "" : NamePrefix);
        vals.add((NameSuffix == null) ? "" : NameSuffix);
        vals.add((Initials == null) ? "" : Initials);
        vals.add((Nickname == null) ? "" : Nickname);
        vals.add((ShortName == null) ? "" : ShortName);
        vals.add((MaidenName == null) ? "" : MaidenName);
        vals.add((Birthday == null) ? "" : Birthday);
        vals.add((Gender == null) ? "" : Gender);
        vals.add((Location == null) ? "" : Location);
        vals.add((BillingInformation == null) ? "" : BillingInformation);
        vals.add((DirectoryServer == null) ? "" : DirectoryServer);
        vals.add((Mileage == null) ? "" : Mileage);
        vals.add((Occupation == null) ? "" : Occupation);
        vals.add((Hobby == null) ? "" : Hobby);
        vals.add((Sensitivity == null) ? "" : Sensitivity);
        vals.add((Priority == null) ? "" : Priority);
        vals.add((Subject == null) ? "" : Subject);
        vals.add((Notes == null) ? "" : Notes);
        vals.add((GroupMembership == null) ? "" : GroupMembership);
        
        int i = 0;
        for(Email e: emails) {
            vals.add(e.type);
            vals.add(e.value);
            i++;
        }
        while(i < numEmails) {
            vals.add("");
            vals.add("");
            i++;
        }
        
        i = 0;
        for(Phone p: phones) {
            vals.add(p.type);
            vals.add(p.value);
            i++;
        }
        while(i < numPhones) {
            vals.add("");
            vals.add("");
            i++;
        }
        
        i = 0;
        for(Organization o: orgs) {
            vals.add(o.Type);
            vals.add(o.Name);
            vals.add(o.YomiName);
            vals.add(o.Title);
            vals.add(o.Department);
            vals.add(o.Symbol);
            vals.add(o.Location);
            vals.add(o.JobDescription);
            i++;
        }
        while(i < numOrgs) {
            vals.add("");
            vals.add("");
            vals.add("");
            vals.add("");
            vals.add("");
            vals.add("");
            vals.add("");
            vals.add("");
            i++;
        }
            
        return vals.toArray(new String[vals.size()]);
    }
    
    public static String[] getHeader() {
        List<String> vals = new ArrayList<String>();
        
        vals.add("Name");
        vals.add("Given Name");
        vals.add("Additional Name");
        vals.add("Family Name");
        vals.add("Yomi Name");
        vals.add("Given Name Yomi");
        vals.add("Additional Name Yomi");
        vals.add("Family Name Yomi");
        vals.add("Name Prefix");
        vals.add("Name Suffix");
        vals.add("Initials");
        vals.add("Nickname");
        vals.add("Short Name");
        vals.add("Maiden Name");
        vals.add("Birthday");
        vals.add("Gender");
        vals.add("Location");
        vals.add("Billing Information");
        vals.add("Directory Server");
        vals.add("Mileage");
        vals.add("Occupation");
        vals.add("Hobby");
        vals.add("Sensitivity");
        vals.add("Priority");
        vals.add("Subject");
        vals.add("Notes");
        vals.add("Group Membership");
        
        int i = 0;
        while(i < numEmails) {
            vals.add("E-mail " + (i + 1) + " - Type");
            vals.add("E-mail " + (i + 1) + " - Value");
            i++;
        }
        
        i = 0;
        while(i < numPhones) {
            vals.add("Phone " + (i + 1) + " - Type");
            vals.add("Phone " + (i + 1) + " - Value");
            i++;
        }
        
        i = 0;
        while(i < numOrgs) {
            vals.add("Organization " + (i + 1) + " - Type");
            vals.add("Organization " + (i + 1) + " - Name");
            vals.add("Organization " + (i + 1) + " - Yomi Name");
            vals.add("Organization " + (i + 1) + " - Title");
            vals.add("Organization " + (i + 1) + " - Department");
            vals.add("Organization " + (i + 1) + " - Symbol");
            vals.add("Organization " + (i + 1) + " - Location");
            vals.add("Organization " + (i + 1) + " - Job Description");
            i++;
        }
            
        return vals.toArray(new String[vals.size()]);
    }

    private static class Email {
        public String type = "";
        public String value = "";
    }
    
    private static class Phone {
        public String type = "";
        public String value = "";
    }
    
    private static class Organization {
        public String Type = "";
        public String Name = "";
        public String YomiName = "";
        public String Title = "";
        public String Department = "";
        public String Symbol = "";
        public String Location = "";
        public String JobDescription = "";
    }
}
