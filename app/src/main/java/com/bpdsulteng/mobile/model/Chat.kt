package com.bpdsulteng.mobile.model

import com.fasterxml.jackson.annotation.JsonProperty

class Chat(@JsonProperty("sender") var sender: String? = null,
           @JsonProperty("receiver") var receiver: String? = null,
           @JsonProperty("message") var message: String? = null,
           @JsonProperty("imageurl") var imageurl: String? = null,
           @JsonProperty("isIsseen") var isIsseen: Boolean = false)
