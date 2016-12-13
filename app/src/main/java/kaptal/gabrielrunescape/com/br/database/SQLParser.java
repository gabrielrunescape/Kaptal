package kaptal.gabrielrunescape.com.br.database;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.io.InputStreamReader;
import android.content.res.Resources;

/**
 * <h1>Classe CustomSQLiteOpenHelper</h1>
 *
 * A clase realiza a leitura dos scripts de atualização/criação do banco de dados.
 *
 * Baseado em: http://www.michenux.net/android-database-sqlite-creation-upgrade-245.html
 *
 * @author Michenux
 * @version 0.2
 * @since 2016-08-13
 */
public class SQLParser {
    public static List<String> parseSqlFile(int sqlFile, Resources res) throws IOException {
        List<String> sqlIns = null;
        InputStream is = res.openRawResource(sqlFile);

        try {
            sqlIns = parseSqlFile(is);
        } finally {
            is.close();
        }

        return sqlIns;
    }

    public static int fileToResource(String sqlFile, Class<?> c) {
        try {
            Field field = c.getField(sqlFile);

            return field.getInt(null);
        } catch (Exception ex) {
            ex.printStackTrace();

            return 0;
        }
    }

    public static List<String> parseSqlFile(InputStream is) throws IOException {
        String script = removeComments(is);
        return splitSqlScript(script, ';');
    }

    private static String removeComments(InputStream is) throws IOException {
        StringBuilder sql = new StringBuilder();
        InputStreamReader isReader = new InputStreamReader(is);

        try {
            BufferedReader buffReader = new BufferedReader(isReader);

            try {
                String line;
                String multiLineComment = null;

                while ((line = buffReader.readLine()) != null) {
                    line = line.trim();

                    if (multiLineComment == null) {
                        if (line.startsWith("/*")) {
                            if (!line.endsWith("}")) {
                                multiLineComment = "/*";
                            }
                        } else if (line.startsWith("{")) {
                            if (!line.endsWith("}")) {
                                multiLineComment = "{";
                            }
                        } else if (!line.startsWith("--") && !line.equals("")) {
                            sql.append(line);
                        }
                    } else if (multiLineComment.equals("/*")) {
                        if (line.endsWith("*/")) {
                            multiLineComment = null;
                        }
                    } else if (multiLineComment.equals("{")) {
                        if (line.endsWith("}")) {
                            multiLineComment = null;
                        }
                    }
                }
            } finally {
                buffReader.close();
            }
        } finally {
            isReader.close();
        }

        return sql.toString();
    }

    private static List<String> splitSqlScript(String script, char delim) {
        List<String> statements = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean inLiteral = false;
        char[] content = script.toCharArray();

        for (int i = 0; i < script.length(); i++) {
            if (content[i] == '\'') {
                inLiteral = !inLiteral;
            }

            if (content[i] == delim && !inLiteral) {
                if (sb.length() > 0) {
                    statements.add(sb.toString().trim());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(content[i]);
            }
        }

        if (sb.length() > 0) {
            statements.add(sb.toString().trim());
        }

        return statements;
    }
}
