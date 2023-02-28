package com.bridzelabz.addressBookFile;


	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Scanner;

	public class  AddressBook {

		ArrayList<Contact> contactList = new ArrayList<>();
	    HashMap<String,ArrayList<Contact>> contactsByCity = new HashMap<>();
	    HashMap<String,ArrayList<Contact>> contactsByState = new HashMap<>();

	    public void addContact() {
	        Contact contactPerson = new Contact();
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter the details of contact person");
	        System.out.print("Enter first name:");
	        contactPerson.setName(sc.next());         
	        System.out.print("Enter Last name:");
	        contactPerson.setLastName(sc.next());
	        System.out.println("Enter the Address : ");
	        contactPerson.setAddress(sc.next());
	        System.out.println("Enter the City : ");
	        contactPerson.setCity(sc.next());
	        System.out.println("Enter the State : ");
	        contactPerson.setState(sc.next());
	        System.out.println("Enter the ZipCode : ");
	        contactPerson.setZipCode(sc.next());
	        System.out.println("Enter the Mobile no : ");
	        contactPerson.setPhoneNo(sc.next());
	        contactList.add(contactPerson);             

	   
	        if(contactsByCity.containsKey(contactPerson.getCity())){          
	            ArrayList<Contact> contacts = contactsByCity.get(contactPerson.getCity());    
	            contacts.add(contactPerson);
	            contactsByCity.put(contactPerson.getCity(), contacts);
	        } else{
	            ArrayList<Contact> list = new ArrayList<>(); 
	            list.add(contactPerson);
	            contactsByCity.put(contactPerson.getCity(), list);
	        }

	        // here this "if" is used to add the person to corresponding state in the contactsByState map
	        if(contactsByState.containsKey(contactPerson.getState())){
	            ArrayList<Contact> contacts = contactsByState.get(contactPerson.getState());
	            contacts.add(contactPerson);
	            contactsByCity.put(contactPerson.getState(), contacts);
	        } else{
	            ArrayList<Contact> list = new ArrayList<>();
	            list.add(contactPerson);
	            contactsByState.put(contactPerson.getState(), new ArrayList<>(list));
	        }
	    }

	    @Override
	    public String toString() {
	        return "AddressBook{" +
	                "contactList=" + contactList +
	                '}';
	    }

	    public void editContact() {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter first name:");
	        String name = sc.next();
	        for (Contact contactPerson : contactList) {
	            if (name.equals(contactPerson.getName())) {  
	                String city = contactPerson.getCity();
	                String state = contactPerson.getState();
	                System.out.println("Set Details");
	                System.out.print("Enter first name:");
	                contactPerson.setName(sc.next());
	                System.out.print("Enter Last name:");
	                contactPerson.setLastName(sc.next());
	                System.out.println("Enter the Address : ");
	                contactPerson.setAddress(sc.next());
	                System.out.println("Enter the City : ");
	                contactPerson.setCity(sc.next());
	                System.out.println("Enter the State : ");
	                contactPerson.setState(sc.next());
	                System.out.println("Enter the ZipCode : ");
	                contactPerson.setZipCode(sc.next());
	                System.out.println("Enter the Mobile no : ");
	                contactPerson.setPhoneNo(sc.next());

	                if(contactsByCity.containsKey(city)){     // here this is used to edit person in the contactByCity map also
	                    for (int i=0; i<contactsByCity.get(city).size();i++){
	                        if(name.equals(contactsByCity.get(city).get(i).getName())){
	                            contactsByCity.get(city).remove(i);
	                            contactsByCity.get(city).add(contactPerson);
	                        }
	                    }
	                }

	                if(contactsByState.containsKey(state)){   // here this is used to edit person in the contactByState map also
	                    for (int i=0; i<contactsByState.get(state).size();i++){
	                        if(name.equals(contactsByState.get(state).get(i).getName())){
	                            contactsByState.get(state).remove(i);
	                            contactsByState.get(state).add(contactPerson);
	                        }
	                    }
	                }


	                break;
	            }
	        }


	    }

	    public void deleteContact() {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter first name:");
	        String name = sc.next();
	        for (Contact contactPerson : contactList) {
	            if (name.equals(contactPerson.getName())) {
	                contactList.remove(contactPerson);       // to remove person from contact list
	                if(contactsByState.containsKey(contactPerson.getState())){      // to remove the person from  contactByState map
	                    ArrayList<Contact> contacts = contactsByState.get(contactPerson.getState());
	                    contacts = new ArrayList<>(contacts.stream().filter(x-> x.getName() != contactPerson.getName()).toList());
	                    contactsByState.put(contactPerson.getState(), contacts);
	                }
	                if(contactsByCity.containsKey(contactPerson.getCity())){     //  // to remove person from contact list
	                    ArrayList<Contact> contacts = contactsByCity.get(contactPerson.getCity());
	                    contacts = new ArrayList<>(contacts.stream().filter(x-> x.getName() != contactPerson.getName()).toList());
	                    contactsByCity.put(contactPerson.getCity(), contacts);
	                }
	                break;
	            }

	        }
	    }

	    public void sortByName(){
	        //contactList.sort((person1,person2)-> person1.getName().compareTo(person2.getName()));
	        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getName().compareTo(person2.getName())).toList());
	    }

	    public void sortByZip(){
	        
	        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getZipCode().compareTo(person2.getZipCode())).toList());
	    }

	    public void sortByCity(){
	        
	        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getCity().compareTo(person2.getCity())).toList());
	    }

	    public void sortByState(){
	        
	        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getState().compareTo(person2.getState())).toList());
	    }

	    public void  operation(){
	        Scanner scanner = new Scanner(System.in);
	        int opration;
	        do {
	            System.out.println(
	                    "1. ADD CONTACT \n2. DISPLAY CONTACT \n3 EDIT \n4 Delete \n 5 Display by city " +
	                            "\n 6 Display by state \n 7 Sort by \n8. EXIT ");
	            System.out.println("Enter the Operation Number");
	            opration = scanner.nextInt();
	            switch (opration) {
	                case 1:
	                    addContact();
	                    break;
	                case 2:
	                    System.out.println(this);
	                    break;
	                case 3:
	                    editContact();
	                    break;
	                case 4:
	                    deleteContact();
	                    break;
	                case 5:
	                    System.out.println("Enter the  city");
	                    String city = scanner.next();
	                    System.out.println(contactsByCity.get(city));  // get method will give contact person list for the city
	                    System.out.println("Number of contacts with city = " + city + " " + contactsByCity.get(city).size());
	                    break;
	                case 6:
	                    System.out.println("Enter the  state");
	                    String state = scanner.next();
	                    System.out.println(contactsByState.get(state));
	                    System.out.println("Number of contacts with state = " + state + " " + contactsByState.get(state).size());
	                    break;
	                case 7:
	                    System.out.println("1. Name");
	                    System.out.println("2. City");
	                    System.out.println("3. State");
	                    System.out.println("4. Zip");
	                    int opt = scanner.nextInt();
	                    switch (opt){
	                        case 1:
	                            this.sortByName();
	                            break;
	                        case 2:
	                            this.sortByCity();
	                            break;
	                        case 3:
	                            this.sortByState();
	                            break;
	                        case 4:
	                            this.sortByZip();
	                            break;
	                    }
	                    System.out.println(this);
	                    break;
	                case 8:
	                    System.out.println("Exiting");
	                    break;
	                default:
	                    System.out.println("Enter The Wrong Opration Number");
	            }
	        } while (opration != 8);
	    }

	}
  


