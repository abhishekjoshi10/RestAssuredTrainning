package day2;

public class PojoUser {
	   private String name;
	    private int age;

	    // Constructor
	    public PojoUser(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }

	    // Getters and setters
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public int getAge() { return age; }
	    public void setAge(int age) { this.age = age; }

	    // equals & hashCode for deletion & comparison in tests
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof PojoUser)) return false;
	        PojoUser user = (PojoUser) o;
	        return age == user.age && name.equals(user.name);
	    }

	    @Override
	    public int hashCode() {
	        return name.hashCode() + age;
	    }
}
