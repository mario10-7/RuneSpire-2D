package com.foo.server.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.foo.server.network.Database;
import com.foo.server.utilities.DAO;

public class UserDAO extends DAO {

	public UserDAO(Database database) {
		super(database);
	}

	public UserDAO(String path, String format) {
		super(path, format);
	}

	public User getUser(String userID) throws UserNotFoundException,
			IOException {
		if (database != null) {
			return null;
		} else if (database == null && path != null) {
			File userFile = new File(this.path + userID + format);
			if (!userFile.exists())
				throw new UserNotFoundException("User not found! UserID: "
						+ userID);
			User user = new User();
			user.setUsername(userID);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					userFile));
			String lineRead = null;
			while ((lineRead = bufferedReader.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(lineRead);
				switch ((String) stringTokenizer.nextElement()) {
				case "[PASSWORD]:":
					user.setPassword(stringTokenizer.nextToken());
					break;
				case "[EMAIL]:":
					user.setEmail(stringTokenizer.nextToken());
					break;
				}
			}
			bufferedReader.close();
			return user;

		}
		return null;
	}
}