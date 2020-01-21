package org.hisp.dhis.setting;

/*
 * Copyright (c) 2004-2020, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.LocaleUtils;
import org.hisp.dhis.analytics.AnalyticsFinancialYearStartKey;
import org.hisp.dhis.common.DigitGroupSeparator;
import org.hisp.dhis.common.cache.Cacheability;
import org.hisp.dhis.configuration.Configuration;
import org.hisp.dhis.fileresource.FileResourceRetentionStrategy;
import org.hisp.dhis.i18n.locale.LocaleManager;
import org.hisp.dhis.sms.config.SmsConfiguration;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * @author Lars Helge Overland
 */
public enum SettingKey
{
    UI_LOCALE( "keyUiLocale", LocaleManager.DEFAULT_LOCALE, Locale.class ),
    DB_LOCALE( "keyDbLocale", Locale.class ),
    ANALYSIS_DISPLAY_PROPERTY( "keyAnalysisDisplayProperty", "name", String.class ),
    ANALYSIS_DIGIT_GROUP_SEPARATOR( "keyAnalysisDigitGroupSeparator", DigitGroupSeparator.SPACE, DigitGroupSeparator.class ),
    CURRENT_DOMAIN_TYPE( "keyCurrentDomainType" ),
    AUTO_SAVE_CASE_ENTRY_FORM( "keyAutoSaveCaseEntryForm", Boolean.FALSE, Boolean.class ),
    AUTO_SAVE_TRACKED_ENTITY_REGISTRATION_ENTRY_FORM( "keyAutoSavetTrackedEntityForm", Boolean.FALSE, Boolean.class ),
    AUTO_SAVE_DATA_ENTRY_FORM( "keyAutoSaveDataEntryForm", Boolean.FALSE, Boolean.class ),
    TRACKER_DASHBOARD_LAYOUT( "keyTrackerDashboardLayout" ),
    APPLICATION_TITLE( "applicationTitle", "DHIS 2", String.class, false, true ),
    APPLICATION_INTRO( "keyApplicationIntro", true ),
    APPLICATION_NOTIFICATION( "keyApplicationNotification", true ),
    APPLICATION_FOOTER( "keyApplicationFooter", true ),
    APPLICATION_RIGHT_FOOTER( "keyApplicationRightFooter", true ),
    FLAG( "keyFlag", "dhis2", String.class ),
    FLAG_IMAGE( "keyFlagImage" ),
    START_MODULE( "startModule", "dhis-web-dashboard", String.class ),
    FACTOR_OF_DEVIATION( "factorDeviation", 2d, Double.class ),
    EMAIL_HOST_NAME( "keyEmailHostName" ),
    EMAIL_PORT( "keyEmailPort", 587, Integer.class ),
    EMAIL_USERNAME( "keyEmailUsername" ),
    EMAIL_TLS( "keyEmailTls", Boolean.TRUE, Boolean.class ),
    EMAIL_SENDER( "keyEmailSender", "no-reply@dhis2.org", String.class ),
    EMAIL_PASSWORD( "keyEmailPassword", "", String.class, true, false ),
    MIN_PASSWORD_LENGTH( "minPasswordLength", 8, Integer.class ),
    MAX_PASSWORD_LENGTH( "maxPasswordLength", 40, Integer.class ),
    SMS_CONFIG( "keySmsSetting", SmsConfiguration.class ),
    CACHE_STRATEGY( "keyCacheStrategy", "CACHE_6AM_TOMORROW", String.class ),
    CACHEABILITY( "keyCacheability", Cacheability.PUBLIC, Cacheability.class ),
    CACHE_ANALYTICS_DATA_YEAR_THRESHOLD( "keyCacheAnalyticsDataYearThreshold", 0, Integer.class ),
    ANALYTICS_FINANCIAL_YEAR_START( "analyticsFinancialYearStart", AnalyticsFinancialYearStartKey.FINANCIAL_YEAR_OCTOBER, AnalyticsFinancialYearStartKey.class ),
    PHONE_NUMBER_AREA_CODE( "phoneNumberAreaCode" ),
    MULTI_ORGANISATION_UNIT_FORMS( "multiOrganisationUnitForms", Boolean.FALSE, Boolean.class ),
    CONFIGURATION( "keyConfig", Configuration.class ),
    ACCOUNT_RECOVERY( "keyAccountRecovery", Boolean.FALSE, Boolean.class ),
    LOCK_MULTIPLE_FAILED_LOGINS( "keyLockMultipleFailedLogins", Boolean.FALSE, Boolean.class ),
    GOOGLE_ANALYTICS_UA( "googleAnalyticsUA" ),
    CREDENTIALS_EXPIRES( "credentialsExpires", 0, Integer.class ),
    CREDENTIALS_EXPIRY_ALERT( "credentialsExpiryAlert", false, Boolean.class ),
    SELF_REGISTRATION_NO_RECAPTCHA( "keySelfRegistrationNoRecaptcha", Boolean.FALSE, Boolean.class ),
    RECAPTCHA_SECRET( "recaptchaSecret", "6LcVwT0UAAAAAAtMWnPoerWwLx_DSwrcEncHCiWu", String.class ),
    RECAPTCHA_SITE( "recaptchaSite", "6LcVwT0UAAAAAAkO_EGPiYOiymIszZUeHfqWIYX5", String.class ),
    OPENID_PROVIDER( "keyOpenIdProvider" ),
    OPENID_PROVIDER_LABEL( "keyOpenIdProviderLabel" ),
    CAN_GRANT_OWN_USER_AUTHORITY_GROUPS( "keyCanGrantOwnUserAuthorityGroups", Boolean.FALSE, Boolean.class ),
    IGNORE_ANALYTICS_APPROVAL_YEAR_THRESHOLD( "keyIgnoreAnalyticsApprovalYearThreshold", -1, Integer.class ),
    ANALYTICS_MAX_LIMIT( "keyAnalyticsMaxLimit", 100000, Integer.class ),
    SQL_VIEW_MAX_LIMIT( "keySqlViewMaxLimit", -1, Integer.class ),
    RESPECT_META_DATA_START_END_DATES_IN_ANALYTICS_TABLE_EXPORT( "keyRespectMetaDataStartEndDatesInAnalyticsTableExport", Boolean.FALSE, Boolean.class ),
    SKIP_DATA_TYPE_VALIDATION_IN_ANALYTICS_TABLE_EXPORT( "keySkipDataTypeValidationInAnalyticsTableExport", Boolean.FALSE, Boolean.class ),
    SKIP_ZERO_VALUES_IN_ANALYTICS_TABLE_EXPORT( "keySkipZeroValuesInAnalyticsTableExport", Boolean.FALSE, Boolean.class ),
    CUSTOM_LOGIN_PAGE_LOGO( "keyCustomLoginPageLogo", Boolean.FALSE, Boolean.class ),
    CUSTOM_TOP_MENU_LOGO( "keyCustomTopMenuLogo", Boolean.FALSE, Boolean.class ),
    ANALYTICS_MAINTENANCE_MODE( "keyAnalyticsMaintenanceMode", Boolean.FALSE, Boolean.class ),
    DATABASE_SERVER_CPUS( "keyDatabaseServerCpus", 0, Integer.class ),
    LAST_SUCCESSFUL_ANALYTICS_TABLES_RUNTIME( "keyLastSuccessfulAnalyticsTablesRuntime" ),
    LAST_MONITORING_RUN( "keyLastMonitoringRun", Date.class ),
    LAST_SUCCESSFUL_DATA_VALUE_SYNC( "keyLastSuccessfulDataSynch", new Date( 0 ), Date.class ),
    LAST_SUCCESSFUL_EVENT_DATA_SYNC( "keyLastSuccessfulEventsDataSynch", new Date( 0 ), Date.class ),
    LAST_SUCCESSFUL_COMPLETE_DATA_SET_REGISTRATION_SYNC( "keyLastCompleteDataSetRegistrationSyncSuccess", new Date( 0 ), Date.class ),
    SKIP_SYNCHRONIZATION_FOR_DATA_CHANGED_BEFORE( "syncSkipSyncForDataChangedBefore", new Date( 0 ), Date.class ),
    LAST_SUCCESSFUL_ANALYTICS_TABLES_UPDATE( "keyLastSuccessfulAnalyticsTablesUpdate", Date.class ),
    LAST_SUCCESSFUL_RESOURCE_TABLES_UPDATE( "keyLastSuccessfulResourceTablesUpdate", Date.class ),
    LAST_SUCCESSFUL_SYSTEM_MONITORING_PUSH( "keyLastSuccessfulSystemMonitoringPush", Date.class ),
    LAST_SUCCESSFUL_MONITORING( "keyLastSuccessfulMonitoring", Date.class ),
    HELP_PAGE_LINK( "helpPageLink", "https://dhis2.github.io/dhis2-docs/master/en/user/html/dhis2_user_manual_en.html", String.class ),
    ACCEPTANCE_REQUIRED_FOR_APPROVAL( "keyAcceptanceRequiredForApproval", Boolean.FALSE, Boolean.class ),
    SYSTEM_NOTIFICATIONS_EMAIL( "keySystemNotificationsEmail" ),
    ANALYSIS_RELATIVE_PERIOD( "keyAnalysisRelativePeriod", "LAST_12_MONTHS", String.class ),
    REQUIRE_ADD_TO_VIEW( "keyRequireAddToView", Boolean.FALSE, Boolean.class ),
    ALLOW_OBJECT_ASSIGNMENT( "keyAllowObjectAssignment", Boolean.FALSE, Boolean.class ),
    USE_CUSTOM_LOGO_FRONT( "keyUseCustomLogoFront", Boolean.FALSE, Boolean.class ),
    USE_CUSTOM_LOGO_BANNER( "keyUseCustomLogoBanner", Boolean.FALSE, Boolean.class ),
    METADATA_REPO_URL( "keyMetaDataRepoUrl", "https://raw.githubusercontent.com/dhis2/dhis2-metadata-repo/master/repo/221/index.json", String.class ),
    DATA_IMPORT_STRICT_PERIODS( "keyDataImportStrictPeriods", Boolean.FALSE, Boolean.class ),
    DATA_IMPORT_STRICT_DATA_ELEMENTS( "keyDataImportStrictDataElements", Boolean.FALSE, Boolean.class ),
    DATA_IMPORT_STRICT_CATEGORY_OPTION_COMBOS( "keyDataImportStrictCategoryOptionCombos", Boolean.FALSE, Boolean.class ),
    DATA_IMPORT_STRICT_ORGANISATION_UNITS( "keyDataImportStrictOrganisationUnits", Boolean.FALSE, Boolean.class ),
    DATA_IMPORT_STRICT_ATTRIBUTE_OPTION_COMBOS( "keyDataImportStrictAttributeOptionCombos", Boolean.FALSE, Boolean.class ),
    DATA_IMPORT_REQUIRE_CATEGORY_OPTION_COMBO( "keyDataImportRequireCategoryOptionCombo", Boolean.FALSE, Boolean.class ),
    DATA_IMPORT_REQUIRE_ATTRIBUTE_OPTION_COMBO( "keyDataImportRequireAttributeOptionCombo", Boolean.FALSE, Boolean.class ),
    CUSTOM_JS( "keyCustomJs" ),
    CUSTOM_CSS( "keyCustomCss" ),
    CALENDAR( "keyCalendar", "iso8601", String.class ),
    DATE_FORMAT( "keyDateFormat", "yyyy-MM-dd", String.class ),
    APP_STORE_URL( "appStoreUrl", "https://www.dhis2.org/appstore", String.class ),
    APP_STORE_INDEX_URL( "keyAppStoreIndexUrl", "https://s3-eu-west-1.amazonaws.com/dhis2-appstore/appstore.json", String.class ),
    APP_STORE( "keyAppStoreUrl", "https://play.dhis2.org/appstore/", String.class ),
    APP_STORE_API_URL( "keyAppStoreApiUrl", "https://play.dhis2.org/appstore/api/apps", String.class ),
    STYLE( "keyStyle", "light_blue/light_blue.css", String.class ),
    REMOTE_INSTANCE_URL( "keyRemoteInstanceUrl", "", String.class ),
    REMOTE_INSTANCE_USERNAME( "keyRemoteInstanceUsername", "", String.class ),
    REMOTE_INSTANCE_PASSWORD( "keyRemoteInstancePassword", "", String.class, true, false ),
    GOOGLE_MAPS_API_KEY( "keyGoogleMapsApiKey", "AIzaSyBjlDmwuON9lJbPMDlh_LI3zGpGtpK9erc", String.class ),
    GOOGLE_CLOUD_API_KEY( "keyGoogleCloudApiKey", "AIzaSyDWyCSemDgAxocSL7j9Dy4mi93xTTcPEek", String.class ),
    LAST_SUCCESSFUL_METADATA_SYNC( "keyLastMetaDataSyncSuccess", Date.class ),
    METADATAVERSION_ENABLED( "keyVersionEnabled", Boolean.FALSE, Boolean.class ),
    METADATA_FAILED_VERSION( "keyMetadataFailedVersion", String.class ),
    METADATA_LAST_FAILED_TIME( "keyMetadataLastFailedTime", Date.class ),
    LAST_SUCCESSFUL_SCHEDULED_PROGRAM_NOTIFICATIONS( "keyLastSuccessfulScheduledProgramNotifications", Date.class ),
    LAST_SUCCESSFUL_SCHEDULED_DATASET_NOTIFICATIONS( "keyLastSuccessfulScheduledDataSetNotifications", Date.class ),
    REMOTE_METADATA_VERSION( "keyRemoteMetadataVersion", String.class ),
    SYSTEM_METADATA_VERSION( "keySystemMetadataVersion", String.class ),
    STOP_METADATA_SYNC( "keyStopMetadataSync", Boolean.FALSE, Boolean.class ),
    FILE_RESOURCE_RETENTION_STRATEGY( "keyFileResourceRetentionStrategy", FileResourceRetentionStrategy.NONE, FileResourceRetentionStrategy.class ),
    TRACKER_SYNC_PAGE_SIZE( "syncTrackerPageSize", 20, Integer.class ),
    EVENT_SYNC_PAGE_SIZE( "syncEventsPageSize", 60, Integer.class ),
    DATA_VALUES_SYNC_PAGE_SIZE( "syncDataValuesPageSize", 10000, Integer.class ),
    MAX_REMOTE_SERVER_AVAILABILITY_CHECK_ATTEMPTS( "syncMaxRemoteServerAvailabilityCheckAttempts", 3, Integer.class ),
    MAX_SYNC_ATTEMPTS( "syncMaxAttempts", 3, Integer.class ),
    DELAY_BETWEEN_REMOTE_SERVER_AVAILABILITY_CHECK_ATTEMPTS( "syncDelayBetweenRemoteServerAvailabilityCheckAttempts", 500, Integer.class ),
    KEY_SCHED_TASKS( "keySchedTasks" ),
    LAST_SUCCESSFUL_DATA_STATISTICS( "lastSuccessfulDataStatistics", Date.class ),
    LOGGING_LEVEL( "keyLoggingLevel", "INFO", String.class ),
    LOGGING_FORMAT( "keyLoggingFormat", "TEXT", String.class ),
    LOGGING_ADAPTER_CONSOLE( "keyLoggingConsole", Boolean.TRUE, Boolean.class ),
    LOGGING_ADAPTER_CONSOLE_LEVEL( "keyLoggingConsoleLevel", "INFO", String.class ),
    LOGGING_ADAPTER_CONSOLE_FORMAT( "keyLoggingConsoleFormat", "TEXT", String.class ),
    LOGGING_ADAPTER_FILE( "keyLoggingFile", Boolean.FALSE, Boolean.class ),
    LOGGING_ADAPTER_FILE_NAME( "keyLoggingFileName", "dhis2.log", String.class ),
    LOGGING_ADAPTER_FILE_LEVEL( "keyLoggingFileLevel", "INFO", String.class ),
    LOGGING_ADAPTER_FILE_FORMAT( "keyLoggingFileFormat", "JSON", String.class ),
    ANALYTICS_HIDE_DAILY_PERIODS( "keyHideDailyPeriods", Boolean.FALSE, Boolean.class ),
    ANALYTICS_HIDE_WEEKLY_PERIODS( "keyHideWeeklyPeriods", Boolean.FALSE, Boolean.class ),
    ANALYTICS_HIDE_MONTHLY_PERIODS( "keyHideMonthlyPeriods", Boolean.FALSE, Boolean.class ),
    ANALYTICS_HIDE_BIMONTHLY_PERIODS( "keyHideBiMonthlyPeriods", Boolean.FALSE, Boolean.class );

    private final String name;

    private final Serializable defaultValue;

    private final Class<?> clazz;

    private boolean confidential;

    private boolean translatable;

    private static final ImmutableSet<String> NAMES = getNameSet();

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    SettingKey( String name )
    {
        this.name = name;
        this.defaultValue = null;
        this.clazz = String.class;
        this.confidential = false;
        this.translatable = false;
    }

    SettingKey( String name, boolean translatable )
    {
        this.name = name;
        this.defaultValue = null;
        this.clazz = String.class;
        this.confidential = false;
        this.translatable = translatable;
    }

    SettingKey( String name, Class<?> clazz )
    {
        this.name = name;
        this.defaultValue = null;
        this.clazz = clazz;
        this.confidential = false;
        this.translatable = false;
    }

    SettingKey( String name, Serializable defaultValue, Class<?> clazz )
    {
        this.name = name;
        this.defaultValue = defaultValue;
        this.clazz = clazz;
        this.confidential = false;
        this.translatable = false;
    }

    SettingKey( String name, Serializable defaultValue, Class<?> clazz, boolean confidential, boolean translatable )
    {
        this.name = name;
        this.defaultValue = defaultValue;
        this.clazz = clazz;
        this.confidential = confidential;
        this.translatable = translatable;
    }

    // -------------------------------------------------------------------------
    // Logic
    // -------------------------------------------------------------------------

    public static Optional<SettingKey> getByName( String name )
    {
        for ( SettingKey setting : SettingKey.values() )
        {
            if ( setting.getName().equals( name ) )
            {
                return Optional.of( setting );
            }
        }

        return Optional.empty();
    }

    public static Serializable getAsRealClass( String name, String value )
    {
        Optional<SettingKey> setting = getByName( name );

        if ( setting.isPresent() )
        {
            Class<?> settingClazz = setting.get().getClazz();

            if ( Double.class.isAssignableFrom( settingClazz ) )
            {
                return Double.valueOf( value );
            }
            else if ( Integer.class.isAssignableFrom( settingClazz ) )
            {
                return Integer.valueOf( value );
            }
            else if ( Boolean.class.isAssignableFrom( settingClazz ) )
            {
                return Boolean.valueOf( value );
            }
            else if ( Locale.class.isAssignableFrom( settingClazz ) )
            {
                return LocaleUtils.toLocale( value );
            }
            else if ( DigitGroupSeparator.class.isAssignableFrom( settingClazz ) )
            {
                return DigitGroupSeparator.valueOf( value );
            }
            else if ( Cacheability.class.isAssignableFrom( settingClazz ) )
            {
                return Cacheability.valueOf( value );
            }
            else if ( AnalyticsFinancialYearStartKey.class.isAssignableFrom( settingClazz ) )
            {
                return AnalyticsFinancialYearStartKey.valueOf( value );
            }
            else if ( FileResourceRetentionStrategy.class.isAssignableFrom( settingClazz ) )
            {
                return FileResourceRetentionStrategy.valueOf( value );
            }
            else if ( Date.class.isAssignableFrom( settingClazz ) )
            {
                //Accepts String with date in ISO_LOCAL_DATE_TIME format
                LocalDateTime dateTime = LocalDateTime.parse( value );

                return Date.from( dateTime.atZone( ZoneId.systemDefault() ).toInstant() );
            }

            //TODO handle Dates
        }

        return value;
    }

    public boolean hasDefaultValue()
    {
        return defaultValue != null;
    }

    public static Set<String> getNames()
    {
        return NAMES;
    }

    private static ImmutableSet<String> getNameSet()
    {
        return ImmutableSet.copyOf( Sets.newHashSet( SettingKey.values() ).stream()
            .map( s -> s.getName() )
            .collect( Collectors.toSet() ) );
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    public String getName()
    {
        return name;
    }

    public Serializable getDefaultValue()
    {
        return defaultValue;
    }

    public Class<?> getClazz()
    {
        return clazz;
    }

    public boolean isConfidential()
    {
        return confidential;
    }

    public boolean isTranslatable()
    {
        return translatable;
    }

    @Override public String toString()
    {
        return new StringJoiner( ", ", SettingKey.class.getSimpleName() + "[", "]" )
            .add( "name='" + name + "'" )
            .add( "defaultValue=" + defaultValue )
            .add( "clazz=" + clazz )
            .add( "confidential=" + confidential )
            .add( "translatable=" + translatable ).toString();
    }
}