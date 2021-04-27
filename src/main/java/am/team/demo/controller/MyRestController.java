package am.team.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@ResponseBody
public class MyRestController {


    @GetMapping("/{name}")
    public String getFlag(@PathVariable String name) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonStringOfCountry = restTemplate.getForObject("https://restcountries.eu/rest/v2/name/"+name, String.class);
        JsonArray jsonArray = new Gson().fromJson(jsonStringOfCountry, JsonArray.class);
        JsonElement element = jsonArray.get(0);
        String flagHref = element.getAsJsonObject().get("flag").toString();

        return flagHref.substring(1,flagHref.length()-1);
    }
}
