package lab1.BotTelegram;

public class PokemonBot {

    @Override
    public String getBotUsername() {
        return " @Destruc10Bot";
    }

    @Override
    public String getBotToken() {
        return "7365041607:AAGoQgvMDx6MFhXhE0Gr1VHoVzurQxBeJio";
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/pokemon")) {
                // Extraer el nombre del Pokémon del mensaje
                String[] parts = messageText.split(" ");
                if (parts.length == 2) {
                    String pokemonName = parts[1].toLowerCase();
                    String pokemonInfo = getPokemonInfo(pokemonName);
                    sendMessage(chatId, pokemonInfo);
                } else {
                    sendMessage(chatId, "Por favor, usa el formato /pokemon <nombre_del_pokemon>");
                }
            }
        }
    }

    private String getPokemonInfo(String pokemonName) {
        try {
            // Crear la URL para la solicitud a la PokéAPI
            String urlString = String.format("https://pokeapi.co/api/v2/pokemon/%s", pokemonName);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Verificar si la solicitud fue exitosa
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Error: No se pudo obtener la información del Pokémon " + pokemonName;
            }

            // Leer la respuesta de la API
            Scanner scanner = new Scanner(url.openStream());
            String inline = "";
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();

            // Parsear el JSON y obtener la información relevante
            JSONObject json = new JSONObject(inline);
            String name = json.getString("name");
            int id = json.getInt("id");
            int height = json.getInt("height");
            int weight = json.getInt("weight");
            String type = json.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");

            return String.format("Pokémon: %s\nID: %d\nAltura: %d decímetros\nPeso: %d hectogramos\nTipo: %s",
                    name, id, height, weight, type);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: No se pudo obtener la información del Pokémon.";
        }
    }

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

}
