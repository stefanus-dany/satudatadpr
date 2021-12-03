package com.satudata.services.response.login

import com.satudata.services.model.LoginModel

class LoginResponse(
    var response: Boolean,
    var payload: LoginModel
)