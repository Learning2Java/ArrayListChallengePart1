import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contacts> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contacts>();  //arraylist holds the object created from Contacts class
    }

    public boolean addNewContact(Contacts contacts) {  // you are not setting the name and number, you are setting the object which is already created outside the method
        if(findContact(contacts.getName()) >=0) {  //if a contact exists in the array, will return a number greater than 0 and therefore exists in the file
            System.out.println("Contact is already on file");
            return false;
        }
        myContacts.add(contacts);
        return true;
    }

    public boolean updateContact(Contacts oldContact, Contacts newContact) {  // this method actually does the update to the contact
        int foundPosition = findContact(oldContact);
        if(foundPosition < 0) {
            System.out.println(oldContact.getName() + ", was not found.");
            return false;
        } else if(findContact(newContact.getName()) != -1) {
            System.out.println("Contact with name " + newContact.getName() + " already exists. Update was not successful");
            return false;
        }

        this.myContacts.set(foundPosition, newContact);
        System.out.println(oldContact.getName() + ", was replaced with" + newContact.getName());
        return true;
    }

    public boolean removeContact(Contacts contacts) {
        int foundPosition = findContact(contacts);
        if (foundPosition < 0) {
            System.out.println(contacts.getName() + ", was not found.");
            return false;
        }
        this.myContacts.remove(foundPosition);
        System.out.println(contacts.getName() + ", was deleted");
        return true;
    }

    private int findContact(Contacts contacts) {  //returns the index position of contact. Used to edit the arraylist
        return this.myContacts.indexOf(contacts);

    }

    private int findContact(String contactName) {  // returns index position of contact given the name
        for(int i = 0; i < this.myContacts.size(); i++) {
            Contacts contacts = this.myContacts.get(i);
            if(contacts.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public Contacts queryContact(String name) {
        int position = findContact(name);
        if(position>=0) {
            return this.myContacts.get(position);  //key point to the findContact method...this returns the position of the myContact arraylist which has the Contact object (has both name and number)
        }
        return null;
    }

    public String queryContact(Contacts contacts) {
        if(findContact(contacts) >= 0) {
            return contacts.getName();
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List");
        for(int i =0; i<this.myContacts.size(); i++) {
            System.out.println((i+1) + "." +
                            this.myContacts.get(i).getName() + " -> " +
                            this.myContacts.get(i).getPhoneNumber());
        }
    }
}
