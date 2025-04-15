package com.chainxi.system.config.sso;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;


@ConfigurationProperties(prefix = "chainxi.sso")
@Data
public class SsoProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    /**
     * 指定当前系统集成 SSO 时使用的模式（约定型配置项，不对代码逻辑产生任何影响）
     */
    public String mode = "";

    /**
     * 当前 Client 名称标识，用于和 ticket 码的互相锁定
     */
    public Long clientId = 123456789L;
    public String clientSecret="host";

    /**
     * 配置 Server 端主机总地址，拼接在 authUrl、checkTicketUrl、getDataUrl、sloUrl 属性前面，用以简化各种 url 配置
     */
    public String serverUrl="127.0.0.1";

    /**
     * 单独配置 Server 端单点登录授权地址
     */
    public String authUrl = "/sso/auth";

    /**
     * 单独配置 Server 端的 ticket 校验地址
     */
    public String checkTicketUrl = "/sso/checkTicket";

    /**
     * 单独配置 Server 端查询数据 getData 地址
     */
    public String getDataUrl = "/sso/getData";

    /**
     * 单独配置 Server 端单点注销地址
     */
    public String sloUrl = "/sso/signout";

    /**
     * 配置当前 Client 端的登录地址（为空时自动获取）
     */
    public String currSsoLogin;

    /**
     * 配置当前 Client 端的单点注销回调URL （为空时自动获取）
     */
    public String currSsoLogoutCall="/sso/client/logout";

    /**
     * 是否打开单点注销功能
     */
    public Boolean isSlo = true;

    /**
     * 是否打开模式三（此值为 true 时将使用 http 请求：校验ticket值、单点注销、拉取数据getData）
     */
    public Boolean isHttp = false;

    /**
     * 是否校验参数签名（方便本地调试用的一个配置项，生产环境请务必为true）
     */
    public Boolean isCheckSign = true;


}
