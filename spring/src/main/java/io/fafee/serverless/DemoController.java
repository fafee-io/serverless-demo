package io.fafee.serverless;

import io.fafee.serverless.dto.Calculation;
import io.fafee.serverless.dto.Foo;
import io.fafee.serverless.dto.FooBar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/things")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

    private final DemoService demoService;

    @PostMapping
    public ResponseEntity<String> create(Foo foo) {
        return ResponseEntity.created(URI.create("/things/".concat(String.valueOf(demoService.create(foo))))).build();
    }

    @PostMapping("/calculate")
    public ResponseEntity<Calculation> expensive(@RequestParam Long id) {
        return ResponseEntity.ok(demoService.calculate(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Foo foo) {
        demoService.update(id, foo);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Foo>> list() {
        return ResponseEntity.ok(demoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FooBar> get(@PathVariable Long id) {
        return ResponseEntity.ok(demoService.get(id));
    }
}
