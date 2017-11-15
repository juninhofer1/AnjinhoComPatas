package br.com.edu.ifsc.anjinhocompatas.bancodedados;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.UsuarioDao;

public class DbAdapter {

	private static final String TAG = "DbAdapter";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	public static final String DATABASE_NAME = "ANJO_COM_PATAS_MOVEL";
	private static final int DATABASE_VERSION = 1;

	private Context mCtx;

	private class DatabaseHelper extends SQLiteOpenHelper {
		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
			if (!db.isReadOnly()) {
				db.execSQL("PRAGMA foreign_keys=OFF;");
			}
		}

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(UsuarioDao.CREATE_TABLE);
			Log.w(TAG, "DB criado com sucesso!");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, " == Atualizando o banco de dados da versão " + oldVersion + " para " + newVersion);

			switch (newVersion) {
				case 1:
					break;
//				case 2:
//					OnUpgrade002.onUpgrade(db);
//					break;
			}
		}
	}

	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public SQLiteDatabase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		try {
			mDb = mDbHelper.getWritableDatabase();
		} catch (SQLException sqle) {
			throw sqle;
		}
		return mDb;
	}

	public void close() {
		mDbHelper.close();
		mDb.close();
	}
}