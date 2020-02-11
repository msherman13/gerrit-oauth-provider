// Copyright (C) 2015 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

public class GitHub2Api extends DefaultApi20 {
  private static final String AUTHORIZE_URL = "%slogin/oauth/authorize";

  private final String rootUrl;

  public GitHub2Api(String rootUrl) {
    this.rootUrl = rootUrl;
  }

  @Override
  public String getAccessTokenEndpoint() {
    return String.format("%slogin/oauth/access_token", rootUrl);
  }

  @Override
  protected String getAuthorizationBaseUrl() {
    return String.format(AUTHORIZE_URL, rootUrl);
  }

  @Override
  public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
    return OAuth2AccessTokenExtractor.instance();
  }
}
