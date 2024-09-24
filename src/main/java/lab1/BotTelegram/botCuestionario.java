package lab1.BotTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class botCuestionario extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Manejar los comandos
            if (messageText.equals("/hola")) {
                handleHolaCommand(chatId);
            } else if (messageText.startsWith("/info")) {
                handleInfoCommand(chatId);
            } else if (messageText.startsWith("/cambio")) {
                handleCambioCommand(chatId, messageText);
            }
            // Otros comandos...
        }
    }

    // Método para manejar el comando /hola
    private void handleHolaCommand(long chatId) {
        String response = "¡Hola! ¿En qué puedo ayudarte hoy?";
        sendMessage(chatId, response);
    }

    // Método para manejar el comando /info
    private void handleInfoCommand(long chatId) {
        // Reutilizar el mensaje de /hola
        String response = "Este es el comando /info.\n" + getHolaMessage();
        sendMessage(chatId, response);
    }

    // Método para manejar el comando /cambio
    private void handleCambioCommand(long chatId, String messageText) {
        String response;
        // Lógica para manejar el comando /cambio
        if (messageText.split(" ").length > 1) {
            String cantidad = messageText.split(" ")[1]; // Obtener la cantidad a cambiar
            response = "Has solicitado cambiar " + cantidad + " unidades.";
        } else {
            response = "Por favor, proporciona una cantidad para cambiar. Ejemplo: /cambio 100";
        }
        sendMessage(chatId, response);
    }

    // Método auxiliar para obtener el mensaje del comando /hola
    private String getHolaMessage() {
        return "¡Hola! ¿En qué puedo ayudarte hoy?";
    }

    // Método para enviar mensajes
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "@Guerramalagbot";
    }

    @Override
    public String getBotToken() {
        return "7486482922:AAGFYXWXPHaARjfH54ztaaradbOB8nykYxI";
    }
}
