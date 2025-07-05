
# ШАГ 1 CLIENT HELLO
Клиент отправляет:
Поддерживаемые TLS-версии (например, TLS 1.3).
Список шифров (Cipher Suites).
Случайное число (Client Random).

```
"ClientHello": {
  "client version"      : "TLSv1.2",
  "random"              : "B0C8740313C6CA1FFB8A2D4AABB33BB70FA29E658B9EAB77CB5518E39002D2A8",
  
---SSL Session ID — это уникальный идентификатор, используемый в процессе установления и восстановления защищённого соединения по 
--- протоколу SSL/TLS. Его основная цель — оптимизация повторных соединений между клиентом и сервером,
--- чтобы снизить нагрузку на ресурсы и ускорить процесс установления связи. 
  "session id"          : "5B6DD08EE022A1044B3C4F5DF3C4B1AED72EFDF723AB98527DE01F3965990816",
  "cipher suites"       : "[TLS_AES_256_GCM_SHA384(0x1302), TLS_AES_128_GCM_SHA256(0x1301), TLS_CHACHA20_POLY1305_SHA256(0x1303), TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384(0xC02C), TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256(0xC02B), TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256(0xCCA9), TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384(0xC030), TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256(0xCCA8), TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256(0xC02F), TLS_DHE_RSA_WITH_AES_256_GCM_SHA384(0x009F), TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256(0xCCAA), TLS_DHE_DSS_WITH_AES_256_GCM_SHA384(0x00A3), TLS_DHE_RSA_WITH_AES_128_GCM_SHA256(0x009E), TLS_DHE_DSS_WITH_AES_128_GCM_SHA256(0x00A2), TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384(0xC024), TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384(0xC028), TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256(0xC023), TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256(0xC027), TLS_DHE_RSA_WITH_AES_256_CBC_SHA256(0x006B), TLS_DHE_DSS_WITH_AES_256_CBC_SHA256(0x006A), TLS_DHE_RSA_WITH_AES_128_CBC_SHA256(0x0067), TLS_DHE_DSS_WITH_AES_128_CBC_SHA256(0x0040), TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA(0xC00A), TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA(0xC014), TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA(0xC009), TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA(0xC013), TLS_DHE_RSA_WITH_AES_256_CBC_SHA(0x0039), TLS_DHE_DSS_WITH_AES_256_CBC_SHA(0x0038), TLS_DHE_RSA_WITH_AES_128_CBC_SHA(0x0033), TLS_DHE_DSS_WITH_AES_128_CBC_SHA(0x0032), TLS_RSA_WITH_AES_256_GCM_SHA384(0x009D), TLS_RSA_WITH_AES_128_GCM_SHA256(0x009C), TLS_RSA_WITH_AES_256_CBC_SHA256(0x003D), TLS_RSA_WITH_AES_128_CBC_SHA256(0x003C), TLS_RSA_WITH_AES_256_CBC_SHA(0x0035), TLS_RSA_WITH_AES_128_CBC_SHA(0x002F), TLS_EMPTY_RENEGOTIATION_INFO_SCSV(0x00FF)]",
  "compression methods" : "00",
  "extensions"          : [
    "status_request (5)": {
      "certificate status type": ocsp
      "OCSP status request": {
        "responder_id": <empty>
        "request extensions": {
          <empty>
        }
      }
    },
    "supported_groups (10)": {
      "named groups": [x25519, secp256r1, secp384r1, secp521r1, x448, ffdhe2048, ffdhe3072, ffdhe4096, ffdhe6144, ffdhe8192]
    },
    "ec_point_formats (11)": {
      "formats": [uncompressed]
    },
    "status_request_v2 (17)": {
      "cert status request": {
        "certificate status type": ocsp_multi
        "OCSP status request": {
          "responder_id": <empty>
          "request extensions": {
            <empty>
          }
        }
      }
    },
    "extended_master_secret (23)": {
      <empty>
    },
    "session_ticket (35)": {
      <empty>
    },
    "signature_algorithms (13)": {
      "signature schemes": [ecdsa_secp256r1_sha256, ecdsa_secp384r1_sha384, ecdsa_secp521r1_sha512, ed25519, ed448, rsa_pss_rsae_sha256, rsa_pss_rsae_sha384, rsa_pss_rsae_sha512, rsa_pss_pss_sha256, rsa_pss_pss_sha384, rsa_pss_pss_sha512, rsa_pkcs1_sha256, rsa_pkcs1_sha384, rsa_pkcs1_sha512, dsa_sha256, ecdsa_sha1, rsa_pkcs1_sha1, dsa_sha1]
    },
    "supported_versions (43)": {
      "versions": [TLSv1.3, TLSv1.2]
    },
    "psk_key_exchange_modes (45)": {
      "ke_modes": [psk_dhe_ke]
    },
    "signature_algorithms_cert (50)": {
      "signature schemes": [ecdsa_secp256r1_sha256, ecdsa_secp384r1_sha384, ecdsa_secp521r1_sha512, ed25519, ed448, rsa_pss_rsae_sha256, rsa_pss_rsae_sha384, rsa_pss_rsae_sha512, rsa_pss_pss_sha256, rsa_pss_pss_sha384, rsa_pss_pss_sha512, rsa_pkcs1_sha256, rsa_pkcs1_sha384, rsa_pkcs1_sha512, dsa_sha256, ecdsa_sha1, rsa_pkcs1_sha1, dsa_sha1]
    },
    "key_share (51)": {
      "client_shares": [
        {
          "named group": x25519
          "key_exchange": {
            0000: ED E4 2A 43 66 75 E3 3C   F6 C2 3C 66 1B 5D 21 68  ..*Cfu.<..<f.]!h
            0010: DF 36 60 E6 BB A3 03 37   3D AE C7 0D 2E E8 70 59  .6`....7=.....pY
          }
        },
        {
          "named group": secp256r1
          "key_exchange": {
            0000: 04 0A 1B BA 13 7F DA D1   FF 32 1D FB D4 16 5E 73  .........2....^s
            0010: 6D 56 AA CD BE 09 38 C3   08 8C 93 D4 9D B9 C0 0C  mV....8.........
            0020: A9 CF EA 2F 79 20 55 C9   7F 9A 4A 4C D1 BA EB D3  .../y U...JL....
            0030: 13 0B 4B 9A 19 BE 19 00   38 55 D8 15 64 B0 66 72  ..K.....8U..d.fr
            0040: 26
          }
        },
      ]
    }
  ]
}
)
```


# ШАГ 2 SERVER HELLO
Сервер отвечает:
Выбранную TLS-версию и шифр (например, TLS_AES_256_GCM_SHA384).
Свой сертификат (для аутентификации).
Случайное число (Server Random).

```
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.469 MSK|ServerHello.java:892|Consuming ServerHello handshake message (
"ServerHello": {
  "server version"      : "TLSv1.2",
  "random"              : "8F7BBC5477BE54A5139969C48FF838AC366E2247A300FA1744FAF5BC0D4719DC",
  "session id"          : "5B6DD08EE022A1044B3C4F5DF3C4B1AED72EFDF723AB98527DE01F3965990816",
  "cipher suite"        : "TLS_AES_256_GCM_SHA384(0x1302)",
  "compression methods" : "00",
--- Сервер указывает, что фактически поддерживает TLS 1.3, несмотря на изначальное TLSv1.2 в основном поле.
  → Клиент переключится на TLS 1.3.
  "extensions"          : [
    "supported_versions (43)": {
      "selected version": [TLSv1.3]
    },

--- Сервер отправляет свой публичный ключ для алгоритма x25519 (Elliptic Curve Diffie-Hellman на кривой X25519).
--- Это часть обмена ключами по ECDHE (Ephemeral Elliptic-Curve Diffie-Hellman).
    "key_share (51)": {
      "server_share": {
        "named group": x25519
        "key_exchange": {
          0000: 80 D1 F9 43 13 81 E9 7B   E9 22 5F 28 E5 0B D0 B8  ...C....."_(....
          0010: DE 3B 2B 50 B5 76 92 AA   7E 07 D8 EC FC D2 59 25  .;+P.v........Y%
        }
      },
    }
  ]
}

javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.470 MSK|SSLExtensions.java:204|Consumed extension: supported_versions
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.470 MSK|ServerHello.java:988|Negotiated protocol version: TLSv1.3
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: server_name
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: max_fragment_length
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: status_request
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: ec_point_formats
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: application_layer_protocol_negotiation
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: status_request_v2
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: extended_master_secret
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:175|Ignore unsupported extension: session_ticket
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.471 MSK|SSLExtensions.java:204|Consumed extension: supported_versions
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:204|Consumed extension: key_share
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:175|Ignore unsupported extension: renegotiation_info
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|PreSharedKeyExtension.java:922|Handling pre_shared_key absence.
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: server_name
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: max_fragment_length
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: status_request
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: ec_point_formats
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: application_layer_protocol_negotiation
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: status_request_v2
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: extended_master_secret
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.473 MSK|SSLExtensions.java:219|Ignore unavailable extension: session_ticket
javax.net.ssl|WARNING|10|main|2025-07-05 10:07:06.474 MSK|SSLExtensions.java:227|Ignore impact of unsupported extension: supported_versions
javax.net.ssl|WARNING|10|main|2025-07-05 10:07:06.474 MSK|SSLExtensions.java:227|Ignore impact of unsupported extension: key_share
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.474 MSK|SSLExtensions.java:219|Ignore unavailable extension: renegotiation_info
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.474 MSK|SSLExtensions.java:219|Ignore unavailable extension: pre_shared_key
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.477 MSK|SSLCipher.java:1836|KeyLimit read side: algorithm = AES/GCM/NoPadding:KEYUPDATE
countdown value = 137438953472
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.478 MSK|SSLCipher.java:1987|KeyLimit write side: algorithm = AES/GCM/NoPadding:KEYUPDATE
countdown value = 137438953472
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.478 MSK|ChangeCipherSpec.java:244|Consuming ChangeCipherSpec message
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|EncryptedExtensions.java:172|Consuming EncryptedExtensions handshake message (
"EncryptedExtensions": [
  "supported_groups (10)": {
    "named groups": [x25519, secp256r1, secp384r1, secp521r1, x448, ffdhe2048, ffdhe3072, ffdhe4096, ffdhe6144, ffdhe8192]
  }
]
)
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:185|Ignore unavailable extension: server_name
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:185|Ignore unavailable extension: max_fragment_length
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:204|Consumed extension: supported_groups
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:219|Ignore unavailable extension: server_name
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:219|Ignore unavailable extension: max_fragment_length
javax.net.ssl|WARNING|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:227|Ignore impact of unsupported extension: supported_groups
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.484 MSK|SSLExtensions.java:219|Ignore unavailable extension: application_layer_protocol_negotiation

```


# ШАГ 3
Это сообщение Certificate в TLS-рукопожатии, 
где сервер отправляет клиенту свой сертификат (или цепочку сертификатов) для аутентификации.



```
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.485 MSK|CertificateMessage.java:1143|Consuming server Certificate handshake message (
"Certificate": {
  "certificate_request_context": "",
  "certificate_list": [
  {
    "certificate" : {
      "version"            : "v3",
--- Уникальный идентификатор сертификата, заданный Удостоверяющим Центром (CA).
      "serial number"      : "55:c1:78:7a:ce:bd:55:13:6a:09:d3:c3:68:4d:ec:b0:84:81:25:e1",
--- Алгоритм подписи сертификата (здесь — RSA с хешированием SHA-256).
      "signature algorithm": "SHA256withRSA",
--- Кто выпустил сертификат (Удостоверяющий Центр).
      "issuer"             : "CN=My Root CA",
--- Срок действия сертификата.
      "not before"         : "2025-07-02 15:06:12.000 MSK",
      "not  after"         : "2026-07-02 15:06:12.000 MSK",
--- Для кого выпущен сертификат (обычно — доменное имя сервера).
      "subject"            : "CN=localhost",
--- Тип публичного ключа (здесь — RSA).
      "subject public key" : "RSA",
      "extensions"         : [
        {
          ObjectId: 2.5.29.35 Criticality=false
          AuthorityKeyIdentifier [
--- KeyIdentifier: F1 1A 40 FF ... — это хеш публичного ключа CA.
--- Клиент использует это, чтобы найти соответствующий CA в своём TrustStore.
          KeyIdentifier [
          0000: F1 1A 40 FF AC 38 16 68   97 6E EE 93 E7 BE AC 22  ..@..8.h.n....."
          0010: 6B 08 22 09                                        k.".
          ]
          ]
        },
        {
          ObjectId: 2.5.29.14 Criticality=false
          SubjectKeyIdentifier [
--- Идентификатор публичного ключа самого сертификата.
--- KeyIdentifier: C7 27 7E 30 ... — хеш публичного ключа сервера.
--- Нужен для построения цепочки доверия.
          KeyIdentifier [
          0000: C7 27 7E 30 1F 0F DE AA   69 78 FC A0 AA 5E BB 17  .'.0....ix...^..
          0010: 08 8B 67 F2                                        ..g.
          ]
          ]
        }
      ]}
    "extensions": {
      <no extension>
    }
  },
]
}
)
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.485 MSK|SSLExtensions.java:185|Ignore unavailable extension: status_request
```

# ШАГ 4
Это подпись, которую сервер (или клиент в mTLS) генерирует, чтобы доказать,
что он действительно владеет приватным ключом, соответствующим публичному ключу из сертификата.
Фактически: "Вот мой сертификат, а вот доказательство, что у меня есть приватный ключ для него".

🔹 Как формируется эта подпись?
Клиент и сервер к этому моменту согласовали:
Алгоритмы шифрования (из ServerHello).
Параметры ключей (из KeyShare).
Сервер берёт все предыдущие сообщения handshake и хеширует их (с помощью SHA-256).
Подписывает хеш своим приватным ключом, используя rsa_pss_rsae_sha256.
Отправляет подпись в CertificateVerify.

🔹 Что делает клиент?
Получает CertificateVerify и извлекает подпись.
Повторяет хеширование всех предыдущих сообщений handshake.
Проверяет подпись с помощью публичного ключа из сертификата сервера.
Если проверка прошла → сервер аутентифицирован.
Если нет → SSLHandshakeException (например, "Invalid signature").
```
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.491 MSK|CertificateVerify.java:1169|Consuming CertificateVerify handshake message (
"CertificateVerify": {
  "signature algorithm": rsa_pss_rsae_sha256
  "signature": {
    0000: 81 D1 50 39 20 8E 42 A5   69 7B 7D D9 35 2B 8B 33  ..P9 .B.i...5+.3
    0010: BF 2A 4F CD 12 D3 79 FE   58 5D B4 E7 64 21 1C 2E  .*O...y.X]..d!..
    0020: 42 71 1A 7B AC 82 EC E9   00 82 10 8B A6 98 0E 52  Bq.............R
    0030: 7A DA 1A 19 43 D7 B7 D8   52 0F E5 49 A1 50 02 86  z...C...R..I.P..
    0040: 80 47 95 8D E7 74 31 C2   5F D0 72 9A 2C E1 B7 25  .G...t1._.r.,..%
    0050: 23 5B 0B 84 B6 E1 D5 F5   4D A1 86 1B 59 5E 35 80  #[......M...Y^5.
    0060: E9 BA F0 60 2E 2E 7D 84   16 88 34 5B 08 0F 6A EE  ...`......4[..j.
    0070: 20 3F 99 AA A1 EE B8 36   8B 82 9D 17 B2 E7 16 74   ?.....6.......t
    0080: E3 A5 10 83 35 96 E8 AF   58 94 1B A0 3A 9F 69 ED  ....5...X...:.i.
    0090: E2 82 58 09 41 F0 34 3D   FB E3 DB 34 0B 0C 4A CB  ..X.A.4=...4..J.
    00A0: 2B 50 A9 6C E4 80 A5 06   CC A0 91 0D 7F 8B 71 B6  +P.l..........q.
    00B0: 4F 97 4F 72 C0 D0 1C 60   3A 39 92 1A BF 00 FB 6A  O.Or...`:9.....j
    00C0: A3 25 A6 E5 6A F5 46 C1   7A 76 11 D2 D3 B9 54 D9  .%..j.F.zv....T.
    00D0: 7E 16 8D EE 6E 36 36 A9   CD B3 C7 23 90 D7 18 2F  ....n66....#.../
    00E0: D8 D2 C5 5B 2F 74 A1 D1   25 52 96 4D DA 23 00 C8  ...[/t..%R.M.#..
    00F0: 23 FE D2 95 61 A3 B9 82   1C 68 53 9F CD 3A 3B C3  #...a....hS..:;.
    0100: 71 8B E2 1A 43 46 22 12   51 44 2A 8A C3 6B E5 26  q...CF".QD*..k.&
    0110: 46 34 2C A0 8F D5 36 A9   88 77 45 B1 85 7C B1 BE  F4,...6..wE.....
    0120: B5 8B 76 93 D1 30 39 10   BD F0 88 FE 99 B0 03 23  ..v..09........#
    0130: D3 8C CD AD E1 0B 59 B8   99 0B 84 AD 4B 24 8B 10  ......Y.....K$..
    0140: 6A 33 E3 3D 86 EF 99 4F   DB 8D 4F D3 BD 36 71 44  j3.=...O..O..6qD
    0150: 80 1C CD C6 01 9D 8B B4   83 F9 79 5D 33 71 FE 2B  ..........y]3q.+
    0160: 0A 10 6D 5A 5D A0 C0 B9   E6 00 24 F4 E4 43 F8 D1  ..mZ].....$..C..
    0170: FB 78 8D 2C 17 12 EF 86   81 97 85 DC 5A E5 46 EF  .x.,........Z.F.
    0180: 67 E7 65 2B EC D4 E6 43   A5 14 3B 52 15 30 27 C3  g.e+...C..;R.0'.
    0190: B4 B9 C6 FA 83 CF A8 AC   AE FD 42 2C B6 44 AD 92  ..........B,.D..
    01A0: E4 5F 32 A5 50 04 07 CD   01 DA 53 D6 2F F1 1C D8  ._2.P.....S./...
    01B0: 24 75 4C 8F 76 59 2C 05   85 67 52 39 69 CB BB CD  $uL.vY,..gR9i...
    01C0: 89 9D E4 46 BF 82 F5 3C   C9 45 75 72 A3 EE AE 34  ...F...<.Eur...4
    01D0: 27 F0 28 13 E0 F4 9F DB   4A 6D DA CC CA 7F F9 9D  '.(.....Jm......
    01E0: 28 FC AC 28 1F A8 3B EB   49 BD DE 77 D5 86 C0 79  (..(..;.I..w...y
    01F0: E2 D1 BF 98 C7 35 63 52   69 DE B0 55 A1 45 B6 16  .....5cRi..U.E..
  }
}
)

```

Это финальное сообщение Finished в TLS handshake, которое подтверждает, что все предыдущие этапы 
рукопожатия прошли успешно и соединение готово к шифрованному обмену данными. 
Давайте разберём его значение и роль.
```
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.493 MSK|Finished.java:917|Consuming server Finished handshake message (
"Finished": {
  "verify data": {
    0000: 9A F5 7C 5E EE 1F 09 68   82 9D A8 82 1B 37 78 78  ...^...h.....7xx
    0010: 68 2F AB D3 A2 50 70 1F   EC 71 84 47 E7 06 15 89  h/...Pp..q.G....
    0020: BF 6D 8D 32 A6 E5 8C B2   A5 0E AC 4D 2D B4 12 0F  .m.2.......M-...
  }
}
)
```

```
javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.494 MSK|SSLCipher.java:1836|KeyLimit read side: algorithm = AES/GCM/NoPadding:KEYUPDATE
countdown value = 137438953472



javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.494 MSK|Finished.java:687|Produced client Finished handshake message (
"Finished": {
  "verify data": {
    0000: 40 43 F3 3F 0E F1 8C 42   13 D3 7A 0C 0E A4 CC 55  @C.?...B..z....U
    0010: EB 5D 30 61 96 F1 9F 1D   D5 B4 07 24 19 7C E7 34  .]0a.......$...4
    0020: 38 A3 DC CF 15 1D 64 B5   A1 90 26 D7 6C 17 F3 89  8.....d...&.l...
  }
}
)


javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.495 MSK|SSLCipher.java:1987|KeyLimit write side: algorithm = AES/GCM/NoPadding:KEYUPDATE
countdown value = 137438953472


javax.net.ssl|DEBUG|10|main|2025-07-05 10:07:06.512 MSK|NewSessionTicket.java:566|Consuming NewSessionTicket message (
"NewSessionTicket": {
  "ticket_lifetime"      : "86,400",
  "ticket_age_add"       : "<omitted>",
  "ticket_nonce"         : "01",
  "ticket"               : {
    0000: EB 67 23 E6 90 6D 77 77   28 4A A8 8B FF 0F 09 FF  .g#..mww(J......
    0010: A6 F7 65 09 FD 12 66 B4   8C 69 64 83 67 B2 7D 72  ..e...f..id.g..r
    0020: 3F 7B 7A 95 C8 10 95 67   62 7F 28 2A 2C 97 58 89  ?.z....gb.(*,.X.
    0030: 50 D2 A1 02 F4 A7 ED 29   6A AF 15 20 4A 17 9B 06  P......)j.. J...
    0040: 38 5A FB CC F0 29 72 74   FF 4B 07 9C 4C 74 18 57  8Z...)rt.K..Lt.W
    0050: 11 29 6B 0B AB B6 85 0D   EF 2C 0C DF 9C 20 3B 49  .)k......,... ;I
    0060: 84 6E 20 10 30 8C A0 33   AD ED B4 B2 71 C7 59 23  .n .0..3....q.Y#
    0070: FD BA 4E E9 3B DF A1 FD   13 F1 BA CC 84 17 D9 55  ..N.;..........U
    0080: 0A FC 3B 66 4D 73 7A 09   0F 49 61 E1 D7 76 C7 63  ..;fMsz..Ia..v.c
    0090: BA 10 C1 A8 16 97 B1 95   65 F5 7D 0C FE F1 6E FB  ........e.....n.
    00A0: E3 77 33 DB 6D 74 AE F8   61 87 DD 37 80 D1 F7 A0  .w3.mt..a..7....
    00B0: F4 58 D4 DB 6D 69 D9 8B   8F 0B 40 CB 99 3E A5 56  .X..mi....@..>.V
    00C0: 3D 9D C2 AC 31 7F 58 46   62 20 B4 08 AC 33 20 7A  =...1.XFb ...3 z
    00D0: 9C 7C 8D BD 0B F4 08 22   C8 1C 4D CD F3 49 3F 81  ......."..M..I?.
    00E0: FE 09 25 AC D9 39 58 2E   AA D8 57 6A E9 C6 A9 2C  ..%..9X...Wj...,
    00F0: 26 50 08 6A 3B 4A 17 9F   5D BA AE ED 95 24 F3 80  &P.j;J..]....$..
    0100: 69 BE AB 0F CC 5F 79 0A   C5 B5 5E 08 20 E6 5C DC  i...._y...^. .\.
    0110: C1 F8 A9 54 97 BB 72 E7   58 DA BE 35 B4 83 45 99  ...T..r.X..5..E.
    0120: B8 FC 8D D0 14 E6 5F EE   FA 80 4E D7 7C 16 D6 0B  ......_...N.....
    0130: 58 1F 17 E9 14 A9 3B FA   98 B0 C7 E0 A3 59 BA F4  X.....;......Y..
    0140: 45 80 05 7B BD 67 1A B8   9F EA E4 85 DF 48 17 1E  E....g.......H..
    0150: 57 FD 56 08 9F E8 E7 58   BD 65 2D 52 B8 7A 48 2F  W.V....X.e-R.zH/
    0160: 19 E1 FF 99 62 7F D7 84   97 56 9E B6 CC 0A FD 35  ....b....V.....5
    0170: 89 04 18 22 40 D0 B4 67   FE A0 C0 C5 83 1C 6A 10  ..."@..g......j.
    0180: FB 56 65 B9 98 BD 1A 95   C9 8C C0 E6 C9 E2 46 F5  .Ve...........F.
    0190: B5 12 43 16 29 0D 89 20   47 BC 6C 14 9E 11 52 A5  ..C.).. G.l...R.
    01A0: 93 63 D7 A5 23 7B 08 76   79 A6 8A DC DA DA 9A BE  .c..#..vy.......
    01B0: 32 C3 2B A7 29 AC EC C7   1C A5 73 2C 5A E7 AB FB  2.+.).....s,Z...
    01C0: 0C B1 7B 61 0D 85 48 FC   7C FC EB 6F 20 20 19 1E  ...a..H....o  ..
    01D0: AA 98 AF E3 B8 23 AF 00   38 68 B2 20 AA 19 82 19  .....#..8h. ....
    01E0: 26 5C AB 90 B0 07 E1 BC   63 E8 55 73 8E BE A1 11  &\......c.Us....
    01F0: 56 BD 9E 5F 66 8C CA AD   5C F7 87 FA 76 45 B4 7C  V.._f...\...vE..
    0200: 32 F8 BE 4C D7 08 0E BE   A1 A7 42 38 F5 29 0C 42  2..L......B8.).B
    0210: 9F 84 B5 5B 8B 7A AF 6E   8E 30 1C F1 2D F9 B2 DA  ...[.z.n.0..-...
    0220: B5 3E 7E 38 97 6D 0B AA   14 CB 7E 47 6F 8D 8F 12  .>.8.m.....Go...
    0230: 56 16 24 11 E7 CF 35 80   A3 8E 6B 85 93 22 18 47  V.$...5...k..".G
    0240: 81 59 C7 71 DC 72 46 CF   D6 5F 71 21 3F 88 61 FF  .Y.q.rF.._q!?.a.
    0250: CF 94 58 BD 23 E1 D3 EF   61 41 4D 18 34 7C 89 31  ..X.#...aAM.4..1
    0260: 3D D0 96 F0 B7 2A 8A C2   FF 1F 6C B4 D1 47 6C 60  =....*....l..Gl`
    0270: 58 89 CC 07 EC CA BE 04   1B 88 68 6D 25 C1 FE EC  X.........hm%...
    0280: 47 92 54 02 12 BF 6E 7E   44 9D 5F BB A0 C3 3A 46  G.T...n.D._...:F
    0290: 89 F1 2B 59 31 CD AC 3D   E5 64 E6 DA 4C 69 EC 97  ..+Y1..=.d..Li..
    02A0: 7E B5 28 BD 47 6C 25 95   48 D3 B1 08 B6 64 7E 28  ..(.Gl%.H....d.(
    02B0: 7A BC 99 8D 0A EF 55 AE   77 A8 5B 65 A7 9C 81 83  z.....U.w.[e....
    02C0: 38 63 CA 05 F6 58 A0 9A   83 FB 46 82 E1 2A F3 35  8c...X....F..*.5
    02D0: 19 FD 14 84 A4 90 2E A2   87 1A 0E 84 4C 94 37 29  ............L.7)
    02E0: 99 3F F9 3A 34 23 49 18   CF 76 AE 6E F9 AC 40 F1  .?.:4#I..v.n..@.
    02F0: C5 4F 09 41 A4 C6 7F 07   E3 3A A7 F6 5F A0 6E D1  .O.A.....:.._.n.
    0300: 89 C2 FB 2D 16 B0 82 66   2F 00 3F 29 24 CC 3D F2  ...-...f/.?)$.=.
    0310: F9 34 EA A8 75 F6 51 B9   19 AC FB BB CB 0E 3A 67  .4..u.Q.......:g
    0320: 84 53 42 9E 7D 46 3A D8   3B 82 8A 88 E1 A8 CD F8  .SB..F:.;.......
    0330: FB EF 35 D0 EA 62 5E 98   53 4A BC 96 6B 2A 5A 1B  ..5..b^.SJ..k*Z.
    0340: 9B 33 89 37 9E 7F 6F 8F   BB 13 1D EE 5C DC F2 C9  .3.7..o.....\...
    0350: 84 47 5F 41 68 C4 2B E8   52 E5 D2 B6 79 E7 0F 50  .G_Ah.+.R...y..P
    0360: 4E 25 DC 41 E7 DF 9B AF   C5 EE 7A AD 2A 78 69 47  N%.A......z.*xiG
    0370: 9E B0 11 9F 4F CC 47 01   BD 5A B5 96 E4 66 A5 E5  ....O.G..Z...f..
    0380: B0 0B 96 80 85 FA B3 EE   52 A0 8C 28 B9 E1 06 EF  ........R..(....
    0390: E9 D2 1F 8A FE 26 21 DC   B1 E9 61 0C 12 0E D3 0F  .....&!...a.....
    03A0: 4C 89 99 25 35 6D EE 13   98 D1 9E 84 4E 7F AB 16  L..%5m......N...
    03B0: 13 57 A0 97 F6 34 88 7A   B1 6E B7 5C A9 66 2C 65  .W...4.z.n.\.f,e
    03C0: 73 8E 59 85 48 82 0D 1C   FC 34 E5 BB 50 6F 7D 28  s.Y.H....4..Po.(
    03D0: 80 DD DB 98 AA F2 8C 7D   E8 4F 31 DB FE 26 0C A9  .........O1..&..
    03E0: A6 D2 EC 43 AC E1 5F CA   47 1F 6D C2 C1 17 97 AF  ...C.._.G.m.....
    03F0: F2 2A 72 9D D8 69 0A 09   6F 3E E1 91 B8 E6 C9 81  .*r..i..o>......
    0400: BC 53 39 23 E0 B8 69 2F   23 34 19 40 81 01 1D 47  .S9#..i/#4.@...G
    0410: 84 BE C3 E8 B6 92 F1 65   9F EE 94 7A 67 9A F4 26  .......e...zg..&
    0420: D8 89 31 E4 AD 93 EF 63   8C D7 7A 23 8E 5B 7A 9F  ..1....c..z#.[z.
    0430: 6B AC F2 80 4B 1B 58 91   5B E7 E1 52 51 AC 5E 7E  k...K.X.[..RQ.^.
    0440: C3 92 C2 C7 3D B3 3B B9   50 9F 06 AE 02 B7 F5 CD  ....=.;.P.......
    0450: 8E 08 F6 EF 47 91 30 98   55 91 C7 BC 00 3D 48 E9  ....G.0.U....=H.
    0460: 91 A9 9C 53 39 35 EC BD   DB 73 4D CF 62 12 38 3D  ...S95...sM.b.8=
    0470: B4 0A EA 00 C0 C5 8E BD   28 46 07 E3 CC 37 17 34  ........(F...7.4
    0480: AA 58 1A 10 BD 38 56 67   94 71 20 FB 5F 42 3B B6  .X...8Vg.q ._B;.
    0490: 4C 80 72 FF 68 06 87 83   22 CE 93 2A 68 62 CF 4A  L.r.h..."..*hb.J
    04A0: 21 91 65 67 29 61 76 46   24 7D 4E 07 CF 2D 01 ED  !.eg)avF$.N..-..
    04B0: 6F 91 47 3A BE 46 6D 2C   0B 31 B5 52 14 5D C1 79  o.G:.Fm,.1.R.].y
    04C0: BE 06 AF 1A 19 05 19 84   A5 CC 20 B4 3C 7E 4C 5E  .......... .<.L^
    04D0: 79 F0 7D 32 31 75 45 FE   AF 6E 6C C1 2A BF 12 81  y..21uE..nl.*...
    04E0: D0 18 BA DE D3 41 C9 28   D8 A9 D2 4E 17 C6 B7 72  .....A.(...N...r
    04F0: F4 3C 96 A8 3E B6 71 6D   FB 99 2E CB 0F 1A 5F 87  .<..>.qm......_.
    0500: 04 37 38 5F BB 50 E5 6C   6B D2 05 7B 42 23 EA EF  .78_.P.lk...B#..
    0510: 1C 5E AB 60 50 80 9B 29   67 37 F3 AD 6B 8D 89 59  .^.`P..)g7..k..Y
    0520: 62 AB 27 E3 32 15 E6 52   3E 26 81 09 6D E2 7E 45  b.'.2..R>&..m..E
    0530: 68 81 0C CF C4 A1 8A 7D   87 70 11 55 56 11 F5 6D  h........p.UV..m
    0540: 3A 50 C2 BF D0 D9 B7 BC   7F F4 44 5A DD CD 4C 64  :P........DZ..Ld
    0550: 64 F4 BE 5D 9F DE 46 37   FF 0F 16 FD 96 99 68 39  d..]..F7......h9
    0560: 7A 3F 99 60 D4 16 56 98   47 2F 13 31 C1 F5 F2 74  z?.`..V.G/.1...t
    0570: 2A 24 9A 2D 18 A1 DC AE   B9 90 5C 47 78 B0 86 B4  *$.-......\Gx...
    0580: DC 4E D7 AF 8E A3 D5 B6   B7 F2 C4 66 14 B5 8E 80  .N.........f....
    0590: 2A 74 1B 08 19 13 7F 44   84 E5 5D C6 AF 0D 7B AA  *t.....D..].....
    05A0: AB BA 8E 04 06 1F 02 72   A4 5D DD 74 9B 55 BD 6C  .......r.].t.U.l
    05B0: CB 5B ED EE 2E 82 F0 E6   38 BE D0 1D F3 F4 BE 08  .[......8.......
    05C0: 0B A1 1B 96 78 FD 96 57   7D A5 C7 0B 95 3F 21 4C  ....x..W.....?!L
    05D0: 3E F3 76 45 87 D6 7D AC   7A 6E 26 CA 2D 7C 89 C1  >.vE....zn&.-...
    05E0: 59 89 6B 86 01 6E 50 C4   05 ED 0D 8E A7 F2 3D 39  Y.k..nP.......=9
    05F0: 5E
  }  "extensions"           : [
    <no extension>
  ]
}
)
```

