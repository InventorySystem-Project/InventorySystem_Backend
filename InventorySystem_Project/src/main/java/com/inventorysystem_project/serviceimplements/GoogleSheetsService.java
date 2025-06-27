package com.inventorysystem_project.serviceimplements;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value; // <-- Importante
import org.springframework.stereotype.Service;

import java.io.FileInputStream; // <-- Importante
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleSheetsService {

    private static final String APPLICATION_NAME = "Inventory System";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SPREADSHEET_ID = "1esMo2ILu_xNgcvEm6G49QBd8PNhpj_3gHWg7ZtVP04Q";

    // Inyecta la ruta del archivo desde las propiedades/variables de entorno.
    // Si no la encuentra, el valor será null gracias a ":#{null}".
    @Value("${google.credentials.path:#{null}}")
    private String credentialsPath; 

    private Sheets sheetsService;

    public GoogleSheetsService() throws IOException, GeneralSecurityException {
        InputStream in;

        // Si la variable de entorno está presente (en Render), la usa.
        if (credentialsPath != null && !credentialsPath.isEmpty()) {
            System.out.println("Cargando credenciales desde la ruta externa: " + credentialsPath);
            in = new FileInputStream(credentialsPath);
        } else {
            // Si no, la busca en la carpeta 'resources' (para que siga funcionando en local).
            System.out.println("Cargando credenciales desde la ruta interna de resources: /credentials.json");
            in = GoogleSheetsService.class.getResourceAsStream("/credentials.json");
        }

        if (in == null) {
            throw new IOException("No se pudo encontrar el archivo de credenciales. Revisa la ruta.");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/spreadsheets"));

        this.sheetsService = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void appendValues(String sheetName, List<List<Object>> values) throws IOException {
        String range = sheetName + "!A:M";
        ValueRange body = new ValueRange().setValues(values);

        AppendValuesResponse result = sheetsService.spreadsheets().values()
                .append(SPREADSHEET_ID, range, body)
                .setValueInputOption("USER_ENTERED")
                .execute();

        System.out.printf("%d celdas añadidas.%n", result.getUpdates().getUpdatedCells());
    }
}