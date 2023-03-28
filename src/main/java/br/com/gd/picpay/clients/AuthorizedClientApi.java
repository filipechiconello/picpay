package br.com.gd.picpay.clients;

import br.com.gd.picpay.dtos.responses.AuthorizedClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client-api", url = "https://run.mocky.io")
public interface AuthorizedClientApi {

    @GetMapping("/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    AuthorizedClientResponseDTO getMessage();
}
