package com.example.demo.app.core.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@TestConfiguration()
class JasyptConfigTest {

    private StringEncryptor encryptor;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JasyptConfig.class);
        encryptor = context.getBean(StringEncryptor.class);
    }

    @Test
    void check_env() {
        // when
        String encryptionKey = System.getenv("enckey");

        // then
        assertThat(encryptionKey).isNotNull();
        assertThat(encryptionKey).isNotEmpty();
    }

    @Test
    void test_encrypt() {
        // given
        String source = "";

        // when
        String encrypted = encryptor.encrypt(source);
        String decrypt = encryptor.decrypt(encrypted);

        System.out.println(encrypted);
        System.out.println(decrypt);

        // then
        assertThat(encrypted).isNotNull();
        assertThat(encrypted).isNotEmpty();
        assertThat(decrypt).isEqualTo(source);
    }

}