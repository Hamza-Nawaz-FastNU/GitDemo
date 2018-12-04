/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import sun.security.util.Password;
public class Storage 
{
    private File usersFile;
    private ArrayList<User> users;    
    private static Storage storage = null;
    private static String PATH = ("C:\\Users\\Aiman Nawaz\\Desktop\\users.xml");
    private Storage()
    {
        usersFile = new File(PATH);
        try
        {
            if(!usersFile.exists())
            {
                usersFile.createNewFile();
                users = new ArrayList<User>();
            }
            else
            {
                 users = new ArrayList<User>();
                loadUsers();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static Storage getInstance()
    {
        if(storage == null)
            storage = new Storage();
        
        return storage;
    }
    
    public void insertUser(User u)
    {
        if(users != null)
            users.add(u);
        
        saveUsers();
    }
    
    public User getUser(String email)
    {
        for(User u : users)
            if(u.email.equals(email))
                return u;
        
        return null;
    }
    
    private void saveUsers() 
    {
        try
        {
            if(!usersFile.exists())
                usersFile.createNewFile();

            FileWriter writer = new FileWriter(usersFile.getAbsolutePath());
            StringBuilder builder = new StringBuilder();
            builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            builder.append("\n");
            builder.append("<users>");
            builder.append("\n");
            
            for(User u : users)
            {
                builder.append("<user email=\"");
                builder.append(u.email);
                builder.append("\" password=\"");
                builder.append(u.password);
                builder.append("\" phone=\"");
                builder.append(u.phone);
                builder.append("\"/>\n");
            }
            builder.append("</users>\n");
            writer.append(builder.toString());
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void loadUsers() 
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Build Document
            Document document = builder.parse(new File("C:\\Users\\Aiman Nawaz\\Desktop\\users.xml"));

            //Normalize the XML Structure; It's just too important !!
            document.getDocumentElement().normalize();

             //Here comes the root node
             Element root = document.getDocumentElement();
             System.out.println(root.getNodeName());

            //Get all users
            NodeList nList = document.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    //Print each users's detail
                    Element eElement = (Element) node;
                    
                    String email = eElement.getAttribute("email");
                    String password = eElement.getAttribute("password");
                    String phone = eElement.getAttribute("phone");
                    
                    users.add(new User(email , phone , password));
                   
                    
                 }
            }
        }
        catch(Exception e)
        {
            
        }
        
        
        
    }
    
    /*
    
    public boolean matchPassword(String email , String password)
    {
        for(User u : users)
            if(u.email.equals(email) && password.equals(u.password))
                return true;
        
        return false;
    }
    
    public ArrayList<User> getAllUsers()
    {
        return users;
    }
    
    */
    
    public void saveUserInfo(String oldEmail , String newEmail , String newPassword , String newPhone)
    {
        for(User u : users)
        {
            if(u.email.equals(oldEmail))
            {
                if(newEmail != null)
                    u.email = newEmail;
                
                if(newPassword != null)
                    u.password = newPassword;
                
                if(newPhone != null)
                    u.phone = newPhone;
                
                saveUsers();
                return;
            }
        }
    }
    
}        

