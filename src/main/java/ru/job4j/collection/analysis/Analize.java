package ru.job4j.collection.analysis;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        for (int i = 0; i < previous.size(); i++) {
            User user = previous.get(i);
            if (current.contains(user)) {
                continue;
            }
            if (user.id != current.get(i).id) {
                result.deleted++;
            }
            if (user.id == current.get(i).id) {
                result.changed++;
            }
        }
        int d = current.size() - (previous.size() - result.deleted);
        if (d > 0) {
            result.added = d;
        }
        return result;
    }

    public static class User {
        int id;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
