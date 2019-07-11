package com.bpdsulteng.mobile.model

import com.fasterxml.jackson.annotation.JsonProperty

class User(@JsonProperty("id") var id: String? = null,
           @JsonProperty("username") var username: String? = null,
           @JsonProperty("imageURL") var imageURL: String? = null,
           @JsonProperty("status") var status: String? = null,
           @JsonProperty("search") var search: String? = null)
