package me.loterio.randomemoji.repository.impl.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2:Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
                CREATE TABLE github_user (
                    id INTEGER NOT NULL DEFAULT 0, 
                    uid INTEGER PRIMARY KEY NOT NULL DEFAULT 0, 
                    login TEXT NOT NULL DEFAULT '', 
                    avatar_url TEXT NOT NULL DEFAULT ''
                )
                """.trimIndent())
    }
}

