
## Test Accounts

| Username | Password | Role |
| --- | --- | --- |
| `mohamed` | `1234` | Student |
| `ahmed` | `5678` | TA |
| `ali` | `9999` | Professor |


## Screenshots & Demonstration

The screenshots below demonstrate the main login flow, validation, and the three-attempt lockout bonus. Captured using JDoodle’s Java GUI environment.

### Initial Login Form

![Initial Login Form](https://github.com/user-attachments/assets/da0f4a40-bf92-46be-a276-64975036a3b8)

The application starts with empty username and password fields, a role selector, and Login and Cancel buttons.

### Empty Fields Validation

![Empty Fields Validation](https://github.com/user-attachments/assets/b78c2682-b58a-4a62-9d5b-3ef7b508e845)

Clicking Login without entering the required credentials displays a validation message asking the user to fill in all fields.

### Incorrect Password

![Incorrect Password](https://github.com/user-attachments/assets/4edb5cd9-8b2b-411f-b573-1fcf91adcb11)

Entering an incorrect password displays an error and the number of remaining attempts (two after the first failure).

### Maximum Failed Attempts

![Maximum Failed Attempts](https://github.com/user-attachments/assets/73b9d44b-baf8-4a8d-a93c-72e217400d2b)

After three incorrect password entries, the application displays a message explaining that login has been disabled.

### Disabled Login Button

![Disabled Login Button](https://github.com/user-attachments/assets/865d83bd-408f-47de-8d27-49ff3591d79d)

The Login button becomes disabled after the third incorrect password attempt, preventing additional attempts in the current window.

### Successful Authentication

![Successful Authentication](https://github.com/user-attachments/assets/632c22ba-e9c0-4a33-96fe-80b71b2004e2)

When the username, password, and role match a hardcoded user, the application displays a successful login confirmation.

### Welcome Page

![Welcome Page](https://github.com/user-attachments/assets/c8e48bd2-891f-466d-aecc-cf1d927b13da)

After successful authentication, a new window greets the user by username and shows their assigned role.
