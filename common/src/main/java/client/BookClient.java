package client;

import model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="book")
public interface BookClient {

    /**
     * 调用book微服务的book/selById/{id}接口，根据ID取书
     * @param id
     * @return
     */
    @GetMapping("/book/selById/{id}")
    Book selById(@PathVariable("id") Integer id);

}
