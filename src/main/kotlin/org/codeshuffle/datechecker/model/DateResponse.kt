package org.codeshuffle.datechecker.model

data class DateResponse(val year: String,
                        val month: String,
                        val dayOfMonth: String,
                        val hours: String,
                        val minutes: String,
                        val seconds: String,
                        val milli: String,
                        val timezone: String)