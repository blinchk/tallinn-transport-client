package me.laus.tallinn.transport.client

interface ParserClient<T> {
    fun parse(): ArrayList<T>
}