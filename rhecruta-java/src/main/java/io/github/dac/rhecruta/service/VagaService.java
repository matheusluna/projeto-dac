package io.github.dac.rhecruta.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.stream.JsonCollectors;

@Local
@Stateless
public class VagaService {

    public JsonArray vagasPorCidade(JsonArray jsonArray, String nomeCidade) {
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

    public JsonArray vagasComDescricao(JsonArray jsonArray, String query) {
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

    public JsonArray vagasPorEmpresa(JsonArray jsonArray, String nomeEmpresa) {
        return jsonArray.stream()
                .filter(
                        jsonValue -> jsonValue
                                .asJsonObject()
                                .getString("company_name")
                                .equals(nomeEmpresa)
                )
                .collect(JsonCollectors.toJsonArray());
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
