import java.util.Scanner;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class AuthenticationSystem {
    User[] users = new User[15];
    int userCount = 0;
    String[] forbiddenPasswords = {"admin", "pass", "password", "qwerty", "ytrewq"};

    void registerUser(String username, String password) {
        if (userCount >= 15) {
            System.out.println("Помилка: не можна додати більше користувачів.");
            return;
        }
        if (username.length() < 5 || username.indexOf(' ') != -1) {
            System.out.println("Помилка: ім'я має бути не менше 5 символів і без пробілів.");
            return;
        }
        if (!isValidPassword(password)) {
            System.out.println("Помилка: пароль не відповідає вимогам.");
            return;
        }
        users[userCount] = new User(username, password);
        userCount++;
        System.out.println("Користувача додано.");
    }

    void deleteUser(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)) {
                users[i] = users[userCount - 1];
                users[userCount - 1] = null;
                userCount--;
                System.out.println("Користувача видалено.");
                return;
            }
        }
        System.out.println("Помилка: користувача не знайдено.");
    }

    void authenticateUser(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username) && users[i].password.equals(password)) {
                System.out.println("Аутентифікація успішна.");
                return;
            }
        }
        System.out.println("Помилка: невірне ім'я або пароль.");
    }

    boolean isValidPassword(String password) {
        if (password.length() < 10 || password.indexOf(' ') != -1) {
            return false;
        }
        int digitCount = 0;
        boolean hasSpecialChar = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= '0' && c <= '9') {
                digitCount++;
            } else if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                hasSpecialChar = true;
            }
        }
        if (digitCount < 3 || !hasSpecialChar) {
            return false;
        }
        for (int i = 0; i < forbiddenPasswords.length; i++) {
            if (password.toLowerCase().indexOf(forbiddenPasswords[i]) != -1) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        AuthenticationSystem system = new AuthenticationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1 - Додати користувача");
            System.out.println("2 - Видалити користувача");
            System.out.println("3 - Аутентифікувати користувача");
            System.out.println("4 - Вийти");
            System.out.print("Виберіть дію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Введіть ім'я: ");
                String username = scanner.nextLine();
                System.out.print("Введіть пароль: ");
                String password = scanner.nextLine();
                system.registerUser(username, password);
            } else if (choice == 2) {
                System.out.print("Введіть ім'я: ");
                String username = scanner.nextLine();
                system.deleteUser(username);
            } else if (choice == 3) {
                System.out.print("Введіть ім'я: ");
                String username = scanner.nextLine();
                System.out.print("Введіть пароль: ");
                String password = scanner.nextLine();
                system.authenticateUser(username, password);
            } else if (choice == 4) {
                System.out.println("Програма завершена.");
                break;
            } else {
                System.out.println("Невірний вибір.");
            }
        }
        scanner.close();
    }
}
