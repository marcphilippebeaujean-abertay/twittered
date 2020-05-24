package com.socialmediaraiser.twitter.helpers;

import lombok.CustomLog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@CustomLog
public class URLHelper {

    private static final String ROOT_URL = "https://api.twitter.com/1.1";
    private static final String ROOT_URL_V2 = "https://api.twitter.com/labs/2";
    private static final String IDS_JSON = "/ids.json?";
    private static final String SCREEN_NAME = "screen_name";
    private static final String ID = "id";
    private static final String COUNT = "count";
    private static final String LIST_JSON = "/list.json?";
    private static final String SHOW_JSON = "/show.json?";
    private static final String CREATE_JSON = "/create.json?";
    private static final String DESTROY_JSON = "/destroy.json?";
    private static final String RETWEETERS = "/retweeters";
    private static final String FOLLOWERS = "/followers";
    private static final String FRIENDS = "/friends";
    private static final String STATUSES = "/statuses";
    private static final String FRIENDSHIPS = "/friendships";
    private static final String FAVORITES = "/favorites";
    private static final String USERS = "/users";
    private static final String TWEETS = "/tweets";
    private static final String SEARCH = "/search";
    private static final String THIRTY_DAYS = "/30day";
    private static final String FULL_ARCHIVE = "/fullarchive";
    private static final String DEV_ENV_NAME = "/dev"; // @todo config
    private static final String ACCOUNT_ACTIVITY = "/account_activity/all";
    private static final String WEBHOOKS = "/webhooks";
    private static final String USER_ID = "user_id";
    private static final String LOOKUP_JSON = "/lookup.json?";
    private static final String USER_TIMELINE = "/user_timeline.json?";
    private static final String JSON = ".json";
    private static final String TRIM_USER = "trim_user=true";
    private static final String EXCLUDE_RTS = "include_rts=false";
    private static final String USER_FORMAT_DETAILED= "user.format=detailed";
    private static final String TWEET_FORMAT_DETAILED= "tweet.format=detailed";
    private static final String EXPANSIONS_RECENT_TWEET= "expansions=most_recent_tweet_id";
    private static final int MAX_COUNT = 200;
    private static final int RETWEET_MAX_COUNT = 100;
    private static final int MAX_LOOKUP = 100;
    private static final String ALL_USER_FIELDS = "user.fields=id,created_at,username,name,location,url,verified,profile_image_url,public_metrics,pinned_tweet_id,description,protected";
    private static final String ALL_TWEET_FIELDS = "tweet.fields=attachments,author_id,created_at,entities,geo,id,in_reply_to_user_id,lang,possibly_sensitive,public_metrics,referenced_tweets,source,text,withheld,context_annotations";
    public static final String LAST_TWEET_LIST_URL = ROOT_URL + STATUSES + USER_TIMELINE;
    public static final String RATE_LIMIT_URL = ROOT_URL + "/application/rate_limit_status.json";;
    public static final String SEARCH_TWEET_30_DAYS_URL = ROOT_URL + TWEETS + SEARCH + THIRTY_DAYS + DEV_ENV_NAME + JSON;
    public static final String SEARCH_TWEET_FULL_ARCHIVE_URL = ROOT_URL + TWEETS + SEARCH + FULL_ARCHIVE + DEV_ENV_NAME + JSON;
    public static final String SEARCH_TWEET_STANDARD_URL = ROOT_URL + SEARCH + TWEETS + JSON;
    public static final String LIVE_EVENT_URL = ROOT_URL + ACCOUNT_ACTIVITY + DEV_ENV_NAME + WEBHOOKS + JSON;
    public static final String SEARCH_TWEET_7_DAYS_URL = ROOT_URL_V2+TWEETS+SEARCH;
    public static final String GET_BEARER_TOKEN_URL = "https://api.twitter.com/oauth2/token";
    public static final String GET_OAUTH1_TOKEN_URL = "https://api.twitter.com/oauth/request_token";

    public String getFollowUrl(String userId) {
        return ROOT_URL +
                FRIENDSHIPS +
                CREATE_JSON +
                USER_ID + "=" +
                userId;
    }

    public String getUnfollowUrl(String userId) {
        return ROOT_URL +
                FRIENDSHIPS +
                DESTROY_JSON +
                USER_ID + "=" +
                userId;
    }

    public String getUnfollowByUsernameUrl(String userName) {
        return ROOT_URL +
                FRIENDSHIPS +
                DESTROY_JSON +
                SCREEN_NAME + "=" +
                userName;
    }

    public String getFriendshipUrl(String sourceId, String targetId) {
        return ROOT_URL +
                FRIENDSHIPS +
                SHOW_JSON +
                "source_" + ID + "=" +
                sourceId +
                "&target_" + ID + "=" +
                targetId;
    }

    public String getRetweetersUrl(String tweetId){
        return ROOT_URL +
                STATUSES +
                RETWEETERS +
                IDS_JSON +
                ID + "=" +
                tweetId +
                "&" + COUNT + "=" +
                RETWEET_MAX_COUNT;
    }

    public String getFollowerIdsUrl(String userId){
        return ROOT_URL +
                FOLLOWERS +
                IDS_JSON +
                USER_ID + "=" +
                userId;
    }

    public String getFollowerUsersUrl(String userId){
        return ROOT_URL +
                FOLLOWERS +
                LIST_JSON +
                USER_ID + "=" +
                userId +
                "&" + COUNT + "=" +
                MAX_COUNT;
    }

    public String getFollowingIdsUrl(String userId){
        return ROOT_URL +
                FRIENDS +
                IDS_JSON +
                USER_ID + "=" +
                userId;
    }

    public String getFollowingUsersUrl(String userId){
        return ROOT_URL +
                FRIENDS +
                LIST_JSON +
                USER_ID + "=" +
                userId +
                "&" + COUNT + "=" +
                MAX_COUNT;
    }

    public String getUserUrl(String userId) {
        return ROOT_URL_V2 +
                USERS +
                "/" +
                userId +
                "?" +
                "expansions=pinned_tweet_id" +
                "&" +
                ALL_USER_FIELDS;
    }

    public String getUserUrlFromName(String username) {
        return ROOT_URL_V2 +
                USERS +
                "/by/username/" +
                username +
                "?" +
                "expansions=pinned_tweet_id" +
                "&" +
                ALL_USER_FIELDS;
    }

    public String getTweetUrl(String tweetId){
        return ROOT_URL_V2 +
                "/tweets/" +
                tweetId +
                "?" +
                "expansions=author_id" +
                "&" +
                ALL_TWEET_FIELDS +
                "&" +
                ALL_USER_FIELDS;
    }

    public String getUsersUrlbyNames(List<String> names) {
        StringBuilder result = new StringBuilder(ROOT_URL)
                .append(USERS)
                .append(LOOKUP_JSON)
                .append(SCREEN_NAME+"=");
        int i=0;
        while(i<names.size() && i<MAX_LOOKUP){
            String name = names.get(i);
            result.append(name);
            result.append(",");
            i++;
        }
        result.delete(result.length()-1,result.length());
        return result.toString();
    }

    public String getUsersUrlbyIds(List<String> ids) {
        StringBuilder result = new StringBuilder(ROOT_URL)
                .append(USERS)
                .append(LOOKUP_JSON)
                .append(USER_ID+"=");
        int i=0;
        while(i<ids.size() && i<MAX_LOOKUP){
            String id = ids.get(i);
            result.append(id);
            result.append(",");
            i++;
        }
        result.delete(result.length()-1,result.length());
        return result.toString();
    }

    @Deprecated
    public String getTweetInfoUrl(String tweetId) {
        return ROOT_URL +
                STATUSES +
                SHOW_JSON +
                ID + "=" +
                tweetId;
    }

    public String getUserTweetsUrl(String userId, int count){
        return ROOT_URL +
                STATUSES +
                USER_TIMELINE +
                USER_ID + "=" +
                userId +
                "&" + COUNT + "=" +
                count +
                "&" + TRIM_USER +
                "&" + EXCLUDE_RTS;
    }

    public String getUserTweetsUrlV2(String userId, int count){
        throw new UnsupportedOperationException();
    }

    public String getLikeUrl(String tweetId) {
        return ROOT_URL + FAVORITES + CREATE_JSON + ID + "=" + tweetId;
    }

    public String getFavoriteTweetsUrl(String userId, String maxId){
        if(maxId==null || maxId.length()==0){
            return "https://api.twitter.com/1.1/favorites/list.json?count=200&user_id="+userId;
        } else{
            return "https://api.twitter.com/1.1/favorites/list.json?count=200&user_id="+userId+"&max_id="+maxId;
        }
    }
}
