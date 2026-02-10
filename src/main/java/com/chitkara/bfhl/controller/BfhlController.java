package com.chitkara.bfhl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chitkara.bfhl.model.ApiResponse;

@RestController
public class BfhlController {

    private static final Set<String> ALLOWED_KEYS =
            Set.of("fibonacci", "prime", "lcm", "hcf", "AI");

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse> handleBfhl(
            @RequestBody(required = false) Map<String, Object> body) {

        ApiResponse response = new ApiResponse();
        response.setOfficial_email("khushi1956.be23@chitkara.edu.in");

        // 1️⃣ Body must not be empty
        if (body == null || body.isEmpty()) {
            response.setIs_success(false);
            response.setError("Request body is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 2️⃣ Exactly ONE key
        if (body.size() != 1) {
            response.setIs_success(false);
            response.setError("Exactly one key is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 3️⃣ Key must be allowed
        String key = body.keySet().iterator().next();
        if (!ALLOWED_KEYS.contains(key)) {
            response.setIs_success(false);
            response.setError("Invalid key");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // ✅ Validation passed
        response.setIs_success(true);

        Object value = body.get(key);

        switch (key) {

            case "fibonacci":
                response.setData(generateFibonacci((Integer) value));
                break;

            case "prime":
                response.setData(filterPrimes((List<Integer>) value));
                break;

            case "lcm":
                response.setData(calculateLCM((List<Integer>) value));
                break;

            case "hcf":
                response.setData(calculateHCF((List<Integer>) value));
                break;

            case "AI":
                response.setData(
                    "AI is transforming healthcare through intelligent automation."
                );
                break;
        }

        return ResponseEntity.ok(response);
    }

    // ---------------- Helper Methods ----------------

    private List<Integer> generateFibonacci(int n) {
        List<Integer> result = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            result.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return result;
    }

    private List<Integer> filterPrimes(List<Integer> numbers) {
        List<Integer> primes = new ArrayList<>();
        for (int n : numbers) {
            if (n > 1) {
                boolean isPrime = true;
                for (int i = 2; i * i <= n; i++) {
                    if (n % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    primes.add(n);
                }
            }
        }
        return primes;
    }

    private int calculateLCM(List<Integer> numbers) {
        int lcm = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            lcm = (lcm * numbers.get(i)) / gcd(lcm, numbers.get(i));
        }
        return lcm;
    }

    private int calculateHCF(List<Integer> numbers) {
        int hcf = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            hcf = gcd(hcf, numbers.get(i));
        }
        return hcf;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
