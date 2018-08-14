package me.danielhartman.common.core

sealed class RequestType {
    class GET(url:String? = "", queryParams:HashMap<String, String> = HashMap()) : RequestType()
    class POST(url:String? = "", body:Any? = null) : RequestType()
    class PUT(url:String? = "", body:Any? = null): RequestType()
    class DELETE(url:String? = "", body:Any? = null): RequestType()
}
