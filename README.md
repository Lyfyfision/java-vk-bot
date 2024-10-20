
# Java bot

This project is a bot for VKontakte that processes incoming messages and sends them back as responses using the VK API. The bot utilizes the `messages.send` method to send messages and the `Callback API` version >5.81 to handle incoming events. To function on a local machine, the application also employs `ngrok` to tunnel localhost to the internet, allowing VK to connect to your server.
Full VK API features documentation can be found [here](https://dev.vk.com/ru/guide).


## Prerequisites

- Java 11 or higher
- Maven
- Ngrok account and ngrok installed
- User access token and confirmation token for VK API
## Installation

## 1. Clone the repository:
   ```
   git clone https://github.com/Lyfyfision/java-vk-bot.git
   
   ```
## 2. Set up ngrok:
   
   Register at [ngrok](https://ngrok.com/) and obtain an authentication token. Start ngrok to forward the local port, which will be used for VK event processing:
   
   ```bash
   ngrok http 8080
   ```
   Make sure that app is also running on port 8080.
## 3. Configure the Callback API:
   
   Navigate to your VK community settings, set up the Callback API pointing to the address provided by ngrok, and select API version 5.81. Copy the confirmation token.

## 4. Store tokens in VkApiConstants:
   
   You'll need to specify both the access token and the confirmation token for proper functionality in the `VkApiConstants` class. Ensure these values are set correctly for the bot to operate.

## 5. Run the bot:
   
   Configure the project with your tokens and start the project:
   ```bash
   mvn spring-boot:run
   ```

The bot will now process messages and echo their content. Ensure that your server remains running and connected via ngrok.
## Screenshots

Result'll be as follows:

![Screenshot](https://ibb.co/x3X1Ywm)


## Running Tests

To run tests, run the following command

```bash
  mvn test
```

