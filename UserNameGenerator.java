package libraryLending;

import java.util.Random;


public class UserNameGenerator {
	
	public String generateRandomUserId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder username = new StringBuilder("User");
        Random random = new Random();

        // Generate 4 random characters for the username
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            username.append(characters.charAt(index));
        }

        return username.toString();
    }

	

}
