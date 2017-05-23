package com.hd123.oauth2.config;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS;
import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_FIELDS;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;
import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Lists.newArrayList;
import static com.hd123.oauth2.common.HttpMediaType.APPLICATION_JSON_UTF_8;
import static com.hd123.oauth2.common.HttpMediaType.TEXT_XML_VALUE_UTF_8;
import static com.hd123.oauth2.common.HttpParams.CONTENT_TYPE;
import static com.hd123.oauth2.common.ProfileConstants.PRODUCTION;
import static com.hd123.oauth2.util.DateUtil.I18N_DATE_FORMAT;
import static java.time.Clock.systemUTC;
import static java.util.EnumSet.of;
import static java.util.TimeZone.getDefault;
import static java.util.concurrent.TimeUnit.DAYS;
import static javax.servlet.DispatcherType.ASYNC;
import static javax.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.REQUEST;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.springframework.beans.factory.config.BeanDefinition.ROLE_INFRASTRUCTURE;
import static org.springframework.beans.factory.config.BeanDefinition.ROLE_SUPPORT;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Role;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.hd123.oauth2.util.ProfileUtil;

/**
 * 自定义配置
 *
 * @author liyue
 * @since 0.0.1
 */
@Configuration
public class WebConfigurerAdapter {

  @Autowired
  private ProfileUtil profileUtil;

  @Bean
  @Role(ROLE_INFRASTRUCTURE)
  public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
    return new WebMvcConfigurerAdapter() {

      @Override
      public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 在此处定制全局JsonMapper,有需要也可定制XmlMapper(MappingJackson2XmlHttpMessageConverter)
        converters
            .parallelStream()
            .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
            .forEach(
                converter -> {
                  final MappingJackson2HttpMessageConverter cvt = (MappingJackson2HttpMessageConverter) converter;
                  final ObjectMapper objectMapper = cvt.getObjectMapper();
                  // objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL,
                  // JsonTypeInfo.As.PROPERTY);
                  final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(I18N_DATE_FORMAT);
                  objectMapper.setDateFormat(dateTimeFormat);
                  objectMapper.setTimeZone(getDefault());
                  // objectMapper
                  // .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
                  objectMapper.registerModule(new SimpleModule("ObjectIdConverter").addSerializer(
                      ObjectId.class, objectIdSerializer()).addDeserializer(ObjectId.class,
                      objectIdDeserializer()));
                  objectMapper.setSerializationInclusion(NON_NULL);
                  if (profileUtil.isDev()) {
                    objectMapper.configure(INDENT_OUTPUT, true);
                  }
                  objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
                  objectMapper.configure(WRITE_ENUMS_USING_TO_STRING, true);
                  objectMapper.configure(WRITE_NULL_MAP_VALUES, false);
                  objectMapper.configure(WRITE_EMPTY_JSON_ARRAYS, true);
                  objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
                  objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
                  objectMapper.configure(USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
                  objectMapper.configure(FAIL_ON_READING_DUP_TREE_KEY, true);
                  objectMapper.configure(FAIL_ON_NUMBERS_FOR_ENUMS, true);
                  objectMapper.configure(READ_ENUMS_USING_TO_STRING, true);
                  objectMapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

                  objectMapper.configure(AUTO_DETECT_GETTERS, true);
                  objectMapper.configure(AUTO_DETECT_FIELDS, true);
                  objectMapper.configure(ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
                  // 引号
                  objectMapper.configure(ALLOW_SINGLE_QUOTES, true);
                  objectMapper.configure(ALLOW_UNQUOTED_FIELD_NAMES, true);
                  objectMapper.configure(WRITE_NUMBERS_AS_STRINGS, true);
                  objectMapper.configure(QUOTE_NON_NUMERIC_NUMBERS, true);
                });

        super.extendMessageConverters(converters);
      }

      @Role(ROLE_SUPPORT)
      @Description("jackson转换器")
      @Bean(name = "mappingJackson2HttpMessageConverter")
      public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        final List<MediaType> supportedMediaTypes = newArrayList(APPLICATION_JSON,
            APPLICATION_JSON_UTF_8);
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);

        return jsonConverter;
      }

      // ObjectId序列化
      private JsonSerializer<Object> objectIdSerializer() {
        return new JsonSerializer<Object>() {
          @Override
          public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider provider)
              throws IOException {
            jsonGenerator.writeString(obj == null ? null : obj.toString());
          }
        };
      }

      // ObjectId反序列化
      private JsonDeserializer<ObjectId> objectIdDeserializer() {
        return new JsonDeserializer<ObjectId>() {
          @Override
          public ObjectId deserialize(JsonParser jp, DeserializationContext ctxt)
              throws IOException {
            return new ObjectId(jp.readValueAs(String.class));
          }
        };
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new
        // AccessKeyInterceptor()).addPathPatterns("/api/**");
      }

      @Override
      public void configurePathMatch(PathMatchConfigurer configurer) {
        // 定制uri
        // configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
      }

      @Override
      public void configureViewResolvers(ViewResolverRegistry registry) {
      }

      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
      }

      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
      }


    };

  }

  @Bean
  @Role(ROLE_INFRASTRUCTURE)
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setMaxAge(3600L);
    config.addAllowedOrigin("*");
    config.addAllowedHeader(CONTENT_TYPE);
    config.setAllowedMethods(newArrayList(POST.name(), GET.name(), PUT.name(), DELETE.name(),
        PATCH.name(), OPTIONS.name()));
    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
      source.registerCorsConfiguration("/api/**", config);
      source.registerCorsConfiguration("/token", config);
      source.registerCorsConfiguration("/check", config);
      source.registerCorsConfiguration("/v2/api-docs", config);
    }
    return new CorsFilter(source);
  }


  /**
   * Initializes the static resources production Filter.
   */
  private void initStaticResourcesProductionFilter(ServletContext servletContext,
      EnumSet<DispatcherType> disps) {
    final Dynamic staticResourcesProductionFilter = servletContext.addFilter(
        "staticResourcesProductionFilter", new StaticResourcesProductionFilter());

    staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/");
    staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/index.html");
    staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
    staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
    staticResourcesProductionFilter.setAsyncSupported(true);
  }

  /**
   * Initializes the caching HTTP Headers Filter.
   */
  private void initCachingHttpHeadersFilter(ServletContext servletContext,
      EnumSet<DispatcherType> disps) {
    final Dynamic cachingHttpHeadersFilter = servletContext.addFilter("cachingHttpHeadersFilter",
        new CachingHttpHeadersFilter());

    cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/dist/assets/*");
    cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/dist/scripts/*");
    cachingHttpHeadersFilter.setAsyncSupported(true);
  }


  /**
   * This filter is used in production, to serve static resources generated by
   * "grunt build".
   * <p/>
   * <p>
   * It is configured to serve resources from the "dist" directory, which is the
   * Grunt destination directory.
   * </p>
   */
  final class StaticResourcesProductionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      // Nothing to initialize
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
      final HttpServletRequest httpRequest = (HttpServletRequest) request;
      final String contextPath = ((HttpServletRequest) request).getContextPath();
      String requestURI = httpRequest.getRequestURI();
      requestURI = substringAfter(requestURI, contextPath);
      if (equal("/", requestURI)) {
        requestURI = "/index.html";
      }
      request.getRequestDispatcher("/dist" + requestURI).forward(request, response);
    }

    @Override
    public void destroy() {
      // Nothing to destroy
    }

  }

  /**
   * This filter put HTTP cache headers with a long (1 month) expiration time.
   */
  final class CachingHttpHeadersFilter implements Filter {

    // We consider the last modified date is the start up time of the server
    private final long LAST_MODIFIED = systemUTC().millis();

    private final long CACHE_TIME_TO_LIVE = DAYS.toMillis(31L);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      // Nothing to initialize
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
      final HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.setHeader("Cache-Control", "max-age=" + CACHE_TIME_TO_LIVE + ", public");
      httpResponse.setHeader("Pragma", "cache");
      // Setting Expires header, for proxy caching
      httpResponse.setDateHeader("Expires", CACHE_TIME_TO_LIVE + systemUTC().millis());
      // Setting the Last-Modified header, for browser caching
      httpResponse.setDateHeader("Last-Modified", LAST_MODIFIED);

      chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
      // Nothing to initialize
    }

  }

}