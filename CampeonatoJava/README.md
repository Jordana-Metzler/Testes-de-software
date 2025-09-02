
# Campeonato (Java, Console + H2 em memória)

Port do PHP para **Java** com **banco em memória (H2)** – nada local/externo. Ao rodar, o schema é criado automaticamente a partir de `schema.sql` no classpath.

## Como rodar

```bash
mvn clean compile
mvn exec:java
```

- O banco **não persiste** depois que o processo encerra (em memória).
- O schema é aplicado na primeira conexão (veja `Database.java`).
- Nenhuma instalação de MySQL é necessária.

## Onde mudar algo?

- `src/main/java/com/example/campeonato/db/Database.java` → URL do H2 (se quiser trocar o nome do DB em memória).
- `src/main/resources/schema.sql` → DDL da tabela.

## Mapeamento e classes

- `model/Atleta.java` – entidade
- `dao/AtletaDAO.java` – CRUD via JDBC (funciona com H2)
- `Main.java` – menu em console
- `pom.xml` – depende de `com.h2database:h2`

## Observações
- Mantém os campos do projeto original (PHP).
- Se quiser uma versão **web (Spring Boot)**, posso gerar também.
