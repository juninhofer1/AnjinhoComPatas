package br.com.edu.ifsc.anjinhocompatas.dao;

import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BasicDAO {

	protected static SQLiteDatabase mDb;
	protected static Context context;
	private static DbAdapter dbAdapter;

	private static int contador;

	public BasicDAO(Context ctx) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		context = ctx;
	}

	private synchronized static int numeroConexoes(int i) {
		contador = contador + i;
		return contador;
	}

	/**
	 * Utiliza ctx para instânciar uma base de dados. Abre o banco!
	 */
	public synchronized void open() {
		if (mDb == null || (mDb != null && !mDb.isOpen())) {
			dbAdapter = new DbAdapter(context);
			mDb = dbAdapter.open();
		}

		numeroConexoes(+1);

	}
	
	/**
	 * Utiliza ctx para instânciar uma base de dados. Abre o banco!
	 */
//	public synchronized void deleteCompleto() {
//			dbAdapter.deleteCompleto();
//
//	}
	
	/**
	 * Utiliza ctx para instânciar uma base de dados. Abre o banco!
	 */
//	public synchronized void deleteParcial() {
//			dbAdapter.deleteParcial();
//	}
	

	/**
	 * Fecha o acesso a uma base de dados.
	 */
	public synchronized void close() {
		if (mDb != null && mDb.isOpen() && (numeroConexoes(0) == 1)) {
			dbAdapter.close();
			mDb.close();
		}
		numeroConexoes(-1);
	}
	
	/**
	 * Monta uma String com os parâmetros passados.
	 * 
	 * @param keys
	 *            Vetor com as colunas a serem utilizadas no "AND"
	 * @param values
	 *            Vetor com os valores a serem utilizadas no "AND"
	 * @return String contendo a condição.
	 */
	private String condicaoANDBuilderAdicional(String[] keys, String[] values, String adicional) {
		String condicao = new String();
		int parada = values.length < keys.length ? values.length : keys.length;

		for (int i = 0; i < parada; i++) {
			condicao += "UPPER(" + keys[i] + ") = UPPER('" + values[i] + "') AND ";
		}
		if (condicao.length() > 5) {
			condicao = condicao.substring(0, condicao.length() - 5);
		}
		if (adicional != null){
			condicao += adicional;
		}
		return condicao;
	}

	/**
	 * Monta uma String com os parâmetros passados.
	 * 
	 * @param keys
	 *            Vetor com as colunas a serem utilizadas no "AND"
	 * @param values
	 *            Vetor com os valores a serem utilizadas no "AND"
	 * @return String contendo a condição.
	 */
	private String condicaoANDBuilder(String[] keys, String[] values) {
		String condicao = new String();
		int parada = values.length < keys.length ? values.length : keys.length;

		for (int i = 0; i < parada; i++) {
			condicao += "UPPER(" + keys[i] + ") = UPPER('" + values[i] + "') AND ";
		}
		if (condicao.length() > 5) {
			condicao = condicao.substring(0, condicao.length() - 5);
		}
		
		return condicao;
	}

	/**
	 * Monta uma String com os parâmetros passados.
	 * 
	 * @param keys
	 *            Vetor com as colunas a serem utilizadas no "OR"
	 * @param values
	 *            Vetor com os valores a serem utilizadas no "OR"
	 * @return String contendo a condição.
	 */
	private String condicaoORBuilder(String[] keys, String[] values) {
		String condicao = new String();
		int parada = values.length < keys.length ? values.length : keys.length;

		for (int i = 0; i < parada; i++) {
			condicao += "UPPER(" + keys[i] + ") = UPPER('" + values[i]
					+ "') OR ";
		}

		if (condicao.length() > 4) {
			condicao = condicao.substring(0, condicao.length() - 4);
		}
		return condicao;
	}

	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida.
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * @param colunas
	 *            Colunas que devem ser consideradas no retorno da busca.
	 * @param condicao
	 *            Condição que será utilizada para executar a query.
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	private Cursor consultaBasica(String table, String[] colunas,
			String condicao) {
		Log.w("BasicoDAO", "Consultando tabela " + table + " colunas "
				+ colunas + " where " + condicao);
		Cursor mCursor = mDb.query(false, table, colunas, condicao, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}
	
	public Cursor consultaBasicaLimit(String table, String[] colunas,
			String condicao, int limit, String name) {
		Log.w("BasicoDAO", "Consultando tabela " + table + " colunas "
				+ colunas + " where " + condicao);
		Cursor mCursor = mDb.query(false, table, colunas, condicao, null, null, null, name, limit+"");
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida.
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * @param colunas
	 *            Colunas que devem ser consideradas no retorno da busca.
	 * @param condicao
	 *            Condição que será utilizada para executar a query.
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	public Cursor consultaBasica(String table, String[] colunas,
			String condicao, String orderBy) {
		Log.w("BasicoDAO", "Consultando tabela " + table + " colunas "
				+ colunas + " where " + condicao);
		Cursor mCursor = mDb.query(false, table, colunas, condicao, null, null,
				null, orderBy, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida. Exemplo: UPPER(keys[0]) = UPPER('values[0]') AND
	 * UPPER(keys[1]) = UPPER('values[1]')...
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * @param colunas
	 *            Colunas que devem ser consideradas no retorno da busca.
	 * @param keys
	 *            Colunas que deverão conter a palavra-chave definida como
	 *            <b>value</b>.
	 * @param values
	 *            Palavra-chave da busca.
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	protected Cursor consultarAND(String table, String[] colunas,
			String[] keys, String[] values) {
		Cursor mCursor = consultaBasica(table, colunas,	condicaoANDBuilder(keys, values));
		return mCursor;
	}
	
	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida. Exemplo: UPPER(keys[0]) = UPPER('values[0]') AND
	 * UPPER(keys[1]) = UPPER('values[1]')...
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * @param colunas
	 *            Colunas que devem ser consideradas no retorno da busca.
	 * @param keys
	 *            Colunas que deverão conter a palavra-chave definida como
	 *            <b>value</b>.
	 * @param values
	 *            Palavra-chave da busca.
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	protected Cursor consultarANDAdicional(String table, String[] colunas,
			String[] keys, String[] values, String adicional) {
		Cursor mCursor = consultaBasica(table, colunas,	condicaoANDBuilderAdicional(keys, values, adicional));
		return mCursor;
	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida. É utilizado ignore case na comparação do OR. Exemplo:
	 * UPPER(keys[0]) = UPPER('values[0]') OR UPPER(keys[1]) =
	 * UPPER('values[1]')...
	 * 
	 * @param table
	 *            Tabela de onde serão consultados os registros.
	 * @param colunas
	 *            Colunas a serem exibidas.
	 * @param keys
	 *            Vetor com as colunas a serem utilizadas no "OR"
	 * @param values
	 *            Vetor com os valores a serem utilizadas no "OR"
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarOR(String table, String[] colunas, String[] keys,
			String[] values) {
		return consultaBasica(table, colunas, condicaoORBuilder(keys, values));

	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida. É utilizado ignore case na comparação do AND. Exemplo:
	 * likeKey[0] LIKE '%likeValue[0]%' AND UPPER(andKey[0]) =
	 * UPPER('andValue[0]')...
	 * 
	 * @param table
	 *            Tabela de onde serão consultados os registros.
	 * @param likeKeys
	 *            Vetor com as colunas a serem utilizadas no "LIKE"
	 * @param likeValues
	 *            Vetor com os valores a serem utilizados no "LIKE"
	 * @param colunas
	 *            Colunas a serem exibidas.
	 * @param andKeys
	 *            Vetor com as colunas a serem utilizadas no "AND"
	 * @param andValues
	 *            Vetor com os valores a serem utilizadas no "AND"
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarLikeAnd(String table, String[] colunas,
			String[] likeKeys, String[] likeValues, String[] andKeys,
			String[] andValues) {
		String condicao = "";

		int paradaLike = likeKeys.length < likeValues.length ? likeKeys.length
				: likeValues.length;
		if (likeKeys != null && likeValues != null) {
			for (int i = 0; i < paradaLike; i++) {
				condicao += likeKeys[i] + " LIKE '%" + likeValues[i]
						+ "%' AND ";
			}
			condicao = condicao.substring(0, condicao.length() - 5);

		}
		if ((andKeys != null) && (andValues != null) && andKeys.length > 0) {
			condicao = condicao + " AND "
					+ condicaoANDBuilder(andKeys, andValues);
		}

		return consultaBasica(table, colunas, condicao);

	}
	
	protected Cursor consultarLikeAndIniciando(String table, String[] colunas,
			String[] likeKeys, String[] likeValues, String[] andKeys,
			String[] andValues, int limit, String orderBy) {
		String condicao = "";

		int paradaLike = likeKeys.length < likeValues.length ? likeKeys.length
				: likeValues.length;
		if (likeKeys != null && likeValues != null) {
			for (int i = 0; i < paradaLike; i++) {
				condicao += likeKeys[i] + " LIKE '" + likeValues[i]
						+ "%' AND ";
			}
			condicao = condicao.substring(0, condicao.length() - 5);

		}
		if ((andKeys != null) && (andValues != null) && andKeys.length > 0) {
			condicao = condicao + " AND "
					+ condicaoANDBuilder(andKeys, andValues);
		}

		return consultaBasicaLimit(table, colunas, condicao, limit, orderBy);

	}
	
	protected Cursor consultarLikeAndContendo(String table, String[] colunas,
			String[] likeKeys, String[] likeValues, String[] andKeys,
			String[] andValues, int limit, String orderBy) {
		String condicao = "";

		int paradaLike = likeKeys.length < likeValues.length ? likeKeys.length
				: likeValues.length;
		if (likeKeys != null && likeValues != null) {
			for (int i = 0; i < paradaLike; i++) {
				condicao += likeKeys[i] + " LIKE '%" + likeValues[i]
						+ "%' AND ";
			}
			condicao = condicao.substring(0, condicao.length() - 5);

		}
		if ((andKeys != null) && (andValues != null) && andKeys.length > 0) {
			condicao = condicao + " AND "
					+ condicaoANDBuilder(andKeys, andValues);
		}
		Log.e("GUIA", "consultarLikeAndContendo");
		Log.e("GUIA", "condicao: " + condicao);
		return consultaBasicaLimit(table, colunas, condicao, limit, orderBy);

	}

	/**
	 * Método genérico para atualizar entradas nas tabelas baseado na igualdade
	 * entre as colunas (key) passadas e seus valores (values) fornecidos.
	 * Exemplo: UPPER(keys[0]) = UPPER('values[0]') AND UPPER(keys[1]) =
	 * UPPER('values[1]')...
	 * 
	 * @param table
	 *            Tabela onde será executada a deleção.
	 * @param cvalues
	 *            ContentValues contendo o conteúdo de uma linha a ser
	 *            atualizada.
	 * @return retorna o número de linhas atualizadas.
	 */
	protected long atualizar(String table, ContentValues cvalues,
			String[] keys, String[] values) {
		long id = -1;
		id = mDb.update(table, cvalues, condicaoANDBuilder(keys, values), null);
		return id;
	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida.
	 * 
	 * @param tabela
	 *            Tabela de onde serão consultados os registros.
	 * @param colunas
	 *            Colunas a serem exibidas.
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarTodos(String tabela, String[] colunas) {
		return consultaBasica(tabela, colunas, null);
	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida.
	 * 
	 * @param tabela
	 *            Tabela de onde serão consultados os registros.
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarTodos(String tabela) {
		return consultarTodos(tabela, null);
	}

	/**
	 * Método genérico para remover entradas nas tabelas baseadas no id
	 * fornecido. Exemplo: coluna = id
	 * 
	 * @param table
	 *            Tabela onde será executada a deleção.
	 * @param id
	 *            Chave da linha a ser deletada.
	 * @return <b>True</b> se a operação foi bem-sucedida; <b>false</b> em caso
	 *         de erro.
	 */
	protected boolean remover(String table, String coluna, long id) {
		return mDb.delete(table, coluna + " = " + id, null) > 0;
	}

	protected boolean remover(String table, String coluna, String valor) {
		return mDb.delete(table, coluna + " = " + valor, null) > 0;
	}

	/**
	 * Método genérico para remover entradas nas tabelas baseadas no id
	 * fornecido. Exemplo: coluna = id
	 * 
	 * @param table
	 *            Tabela onde será executada a deleção.
	 *            Chave da linha a ser deletada.
	 * @return <b>True</b> se a operação foi bem-sucedida; <b>false</b> em caso
	 *         de erro.
	 */
	protected boolean remover(String table, String[] colunas, String[] valores) {
		return mDb.delete(table, condicaoANDBuilder(colunas, valores), null) > 0;
	}

	/**
	 * Método genérico para remover todas as entradas da tabela fornecida.
	 * 
	 * @param table
	 *            Tabela onde será executada a deleção.
	 * @return <b>True</b> se a operação foi bem-sucedida; <b>false</b> em caso
	 *         de erro.
	 */
	public boolean removerTodos(String table) {
		return mDb.delete(table, null, null) > 0;
	}

	/**
	 * Método genérico para remover todas as entradas da tabela fornecida que o
	 * nome da coluna seja diferente do valor passado.
	 * 
	 * @param table
	 *            Tabela onde será executada a deleção.
	 * @param coluna
	 *            Uma coluna da tabela
	 * @param valor
	 *            Valor a ser utilizado no where coluna != valor
	 * @return <b>True</b> se a operação foi bem-sucedida; <b>false</b> em caso
	 *         de erro.
	 */
	protected boolean removerTodosDiferenteDe(String table, String coluna,
			String valor) {
		return mDb.delete(table, coluna + " != ? ", new String[] { valor }) > 0;
	}

	/**
	 * Método genérico para inserir entradas na tabela fornecida.
	 * 
	 * @param table
	 *            Tabela onde será executada a deleção.
	 * @param values
	 *            ContentValues contendo o conteúdo de uma linha a ser inserida
	 * @return o id se a operação foi bem-sucedida; -1 em caso de erro.
	 */
	protected long inserir(String table, ContentValues values) {
		long result = -9;
		Log.w("BasicoDAO", "INSERT INTO " + table + getInsertFormato(values));

		result = mDb.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE);//Alterado para testes
		if (result == -1) {
			Log.e("BasicDAO", "Não foi possivel inserir na tabela");
		}else{
			Log.i("BasicDAO", "Dados inseridos na tabela");
		}
		return result;  
		
	}
	
//	protected long inserir(String table, ContentValues values) {
//		long result = -9;
////		if(table.equals(PedidoDao.TABELA) || table.equals(ItemPedidoDao.TABELA) || table.equals(TransportadorDao.TABELA))
//		Log.w("BasicoDAO", "INSERT INTO " + table + getInsertFormato(values));
////		return mDb.insert(table, null, values); //TODO:Rever se der problema de sobreposi��o.
//		result = mDb.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_IGNORE);//Alterado para testes
//		if (result == -1) {
//			Log.e("BasicDAO", "N�o foi possivel inserir na tabela");
//		}else{
//			Log.i("BasicDAO", "Dados inseridos na tabela");
//		}
//		return result;  
//	}
	
//	public void addListaProdutos(List<Comentario> lista, String tabela) { 
//
//		SQLiteDatabase db = dh.getWritableDatabase();   
//
//		db.beginTransaction();   
//		for (Comentario comentario : lista) { 
//			ContentValues values = classeToValues(comentario); 
//			db.insert(tabela, null, values); 
//		}   
//		db.setTransactionSuccessful(); 
//		db.endTransaction(); 
//		db.close(); 
//	}
	
	private String getInsertFormato(ContentValues values) {

		String s = " ( ";
		String c = "'";
		for (Entry<String, Object> i : values.valueSet()) {
			s = s + i.getKey() + ",";
			c = c + i.getValue() + "','";
		}
		s = s.substring(0, s.length() - 1);
		c = c.substring(0, c.length() - 2);
		s = s + " ) VALUES (" + c + ");";
		return s;
	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida. É utilizado ignore case na comparação do AND.
	 * 
	 * @param table
	 *            Tabela de onde serão consultados os registros.
	 * @param keys
	 *            Vetor com as colunas a serem utilizadas no "LIKE"
	 * @param values
	 *            Vetor com os valores a serem utilizados no "LIKE"
	 * @param colunas
	 *            Colunas a serem exibidas.
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarLike(String table, String[] colunas,
			String[] keys, String[] values) {

		return consultarLikeAnd(table, colunas, keys, values, null, null);

	}
	
	
	protected Cursor consultarLikeLimitIniciando(String table, String[] colunas,
			String[] keys, String[] values, int limit,  String orderBy) {

		return consultarLikeAndIniciando(table, colunas, keys, values, null, null, limit, orderBy);

	}
	protected Cursor consultarLikeLimitContendo(String table, String[] colunas,
			String[] keys, String[] values, int limit,  String orderBy) {

		return consultarLikeAndContendo(table, colunas, keys, values, null, null, limit, orderBy);

	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida. É utilizado ignore case na comparação do AND.
	 * 
	 * @param table
	 *            Tabela de onde serão consultados os registros.
	 * @param key
	 *            Coluna a ser utilizada no "LIKE"
	 * @param value
	 *            Valor a ser utilizado no "LIKE"
	 * @param colunas
	 *            Colunas a serem exibidas.
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarLike(String table, String[] colunas, String key,
			String value) {
		return consultarLike(table, colunas, new String[] { key },
				new String[] { value });
	}

	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida.
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * @param colunas
	 *            Colunas que devem ser consideradas no retorno da busca.
	 * @param key
	 *            Coluna que deverá conter a palavra-chave definida como
	 *            <b>value</b>.
	 * @param value
	 *            Palavra-chave da busca.
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	protected Cursor consultar(String table, String[] colunas, String key,
			String value) {
		return consultarAND(table, colunas, new String[] { key },
				new String[] { value });

	}

	protected Cursor consultar(String table, String[] colunas, String[] keys,
			String[] values) {
		return consultarAND(table, colunas, keys, values );

	}
	
	protected Cursor consultarAdicional(String table, String[] colunas, String[] keys,
			String[] values, String adicional) {
		return consultarANDAdicional(table, colunas, keys, values, adicional );

	}

	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida. <b>Retorna todas as colunas da tabela.</b>
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * 
	 * @param key
	 *            Coluna que deverá conter a palavra-chave definida como
	 *            <b>value</b>.
	 * @param value
	 *            Palavra-chave da busca.
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	protected Cursor consultar(String table, String key, String value) {
		return consultarAND(table, null, new String[] { key },
				new String[] { value });

	}
	/**<b>Retorna todas as colunas da tabela.</b>*/
	protected Cursor consultar(String table, String[] key, String[] value) {
		return consultarAND(table, null, key, value);

	}

	/**
	 * Método genérico para efetuar consultas às tabelas, utilizando a chave
	 * fornecida. Retorna todas as colunas da tabela.
	 * 
	 * @param table
	 *            Tabela onde será executada a busca.
	 * @param colunas
	 *            Colunas que devem ser consideradas no retorno da busca.
	 * 
	 * @param keys
	 *            Colunas que deverão conter as palavras-chave definidas como
	 *            <b>values</b>.
	 * @param values
	 *            Palavras-chave da busca.
	 * @param orderBy
	 *            Coluna que deve ser utilizada para ordenação dos itens
	 *            retornados
	 * 
	 * @return Cursor na primeira posição, caso algum dado tenha sido
	 *         encontrado. Se nenhum dado foi encontrado, retorna <b>null</b>.
	 */
	protected Cursor consultar(String table, String[] colunas, String[] keys,
			String[] values, String orderBy) {

		return consultaBasica(table, colunas, condicaoANDBuilder(keys, values),
				orderBy);

	}

	protected boolean existe(String tabela, String coluna, String valor) {
		Cursor c = consultar(tabela, coluna, valor);
		if (c != null) {
			c.moveToFirst();
			if (c.isAfterLast()) {
				c.close();
				return false;
			}
		} else {
			return false;
		}
		c.close();
		return true;

	}

	/**
	 * Retorna um Cursor para todos os registros encontrados para a tabela
	 * definida. É utilizado ignore case na comparação do AND. Exemplo:
	 * likeKey[0] LIKE '%likeValue[0]%' AND UPPER(andKey[0]) =
	 * UPPER('andValue[0]')...
	 * 
	 * @param table
	 *            Tabela de onde serão consultados os registros.
	 * @param likeKey
	 *            Coluna a ser utilizada no "LIKE"
	 * @param likeValue
	 *            Valor a ser utilizado no "LIKE"
	 * @param colunas
	 *            Colunas a serem exibidas.
	 * @param andKeys
	 *            Vetor com as colunas a serem utilizadas no "AND"
	 * @param andValues
	 *            Vetor com os valores a serem utilizadas no "AND"
	 * @return Cursor posicionado no primeiro elemento encontrado.
	 */
	protected Cursor consultarLikeAnd(String table, String[] colunas,
			String likeKey, String likeValue, String[] andKeys,
			String[] andValues) {
		return consultarLikeAnd(table, colunas, new String[] { likeKey },
				new String[] { likeValue }, andKeys, andValues);
	}

	protected boolean existeTabela(String tabela) {
		Cursor cursor = mDb.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ tabela + "'", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				return true;
			}
		}
		return false;

	}
	

}
