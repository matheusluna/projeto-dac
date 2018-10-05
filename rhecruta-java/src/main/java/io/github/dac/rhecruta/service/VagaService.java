package io.github.dac.rhecruta.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonCollectors;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.StringReader;

@Local
@Stateless
public class VagaService {

    private final String URL_BASE = "www.pyjobs.com.br/api/jobs/";
    private final WebTarget REQUEST_BASE = ClientBuilder.newClient().target(URL_BASE);

    public JsonArray recuperarTodasVagas() {

        return recuperarVagas();
    }

    public JsonObject recuperarVagaComId(Integer id) {

        Response response = queryVagaComId(id);

        String jsonString = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        return jsonReader.readObject();
    }

    public JsonArray vagasPorCidade(String nomeCidade) {

        JsonArray jsonArray = recuperarVagas();

        return jsonArray.stream()
                .filter(jsonValue -> {
                    String workplace = jsonValue
                            .asJsonObject()
                            .getString("workplace")
                            .toLowerCase();

                    workplace = filterAcentos(workplace);
                    return workplace.contains(nomeCidade);
                })
                .collect(JsonCollectors.toJsonArray());
    }

    public JsonArray vagasComDescricao(String query) {

        JsonArray jsonArray = recuperarVagas();

        return jsonArray.stream()
                .filter(jsonValue -> {
                    String description = jsonValue
                            .asJsonObject()
                            .getString("description")
                            .toLowerCase();

                    description = filterAcentos(description);
                    return description.contains(query);
                })
                .collect(JsonCollectors.toJsonArray());
    }

    public JsonArray vagasPorEmpresa(String nomeEmpresa) {

        JsonArray jsonArray = recuperarVagas();


        return jsonArray.stream()
                .filter(
                        jsonValue -> jsonValue
                                .asJsonObject()
                                .getString("company_name")
                                .equals(nomeEmpresa)
                )
                .collect(JsonCollectors.toJsonArray());
    }

    private JsonArray recuperarVagas() {

        Response response = queryTodasVagas();

        String jsonString = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();

        return jsonObject.getJsonArray("objects");
    }

    private Response queryTodasVagas() {
        return REQUEST_BASE
                .queryParam("limit", 0)
                .request()
                .get();
    }

    private Response queryVagaComId(Integer id) {
        return REQUEST_BASE
                .path(id.toString())
                .request()
                .get();
    }

    private String filterAcentos(String word) {
        return word
                .replaceAll("á", "a")
                .replaceAll("â", "a")
                .replaceAll("ã", "a")
                .replaceAll("à", "a")
                .replaceAll("é", "e")
                .replaceAll("ê", "e")
                .replaceAll("ẽ", "e")
                .replaceAll("ó", "o")
                .replaceAll("ô", "o")
                .replaceAll("õ", "o")
                .replaceAll("í", "i")
                .replaceAll("î", "i")
                .replaceAll("ĩ", "i");
    }

}
