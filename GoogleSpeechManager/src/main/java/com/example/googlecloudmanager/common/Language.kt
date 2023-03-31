package com.example.googlecloudmanager.common


interface ILanguage<T> {
    fun toLocalString(): String
    fun toName(): String
}


enum class Language(var s: String) : ILanguage<Language> {
    Korean("ko-KR") {
        override fun toString(): String {
            return s
        }

        override fun toLocalString(): String {
            return "ko"
        }

        override fun toName(): String {
            return "ko-KR-Wavenet-C"
        }

    },
    English("en-US") {
        override fun toString(): String {
            return s
        }

        override fun toLocalString(): String {
            return "en"
        }

        override fun toName(): String {
            return "en-US-Wavenet-F"
        }
    },
    Japanese("ja-JP") {
        override fun toString(): String {
            return s
        }

        override fun toLocalString(): String {
            return "jp"
        }

        override fun toName(): String {
            return "ja-JP-Wavenet-B"
        }
    },
    Chinese("zh-CN") {
        override fun toString(): String {
            return s
        }

        override fun toLocalString(): String {
            return "ch"
        }
        override fun toName(): String {
            return "cmn-CN-Wavenet-A"
        }
    }
}