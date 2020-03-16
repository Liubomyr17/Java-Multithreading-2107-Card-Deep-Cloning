package com.company;

/*

2107 Card Deep Cloning
Provide the ability to clone an object of class Solution using deep cloning.
The data in the users map must also be cloned.
Do not forget about the equals and hashCode methods for correctly adding elements of type User to the HashMap.

Requirements:
1. The Solution class must support the Cloneable interface.
2. The User class must support the Cloneable interface.
3. In the User class, the clone method must be correctly implemented.
4. The clone method must be correctly implemented in the Solution class.


 */

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            return new User(this.age, this.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;

            User user = (User) o;

            if (age != user.age) return false;
            return name.equals(user.name);
        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution solution = new Solution();
        for (Map.Entry<String, User> pair : this.users.entrySet()) {
            User user = pair.getValue().clone();
            solution.users.put(pair.getKey(), user);
        }
        return solution;
    }
}
