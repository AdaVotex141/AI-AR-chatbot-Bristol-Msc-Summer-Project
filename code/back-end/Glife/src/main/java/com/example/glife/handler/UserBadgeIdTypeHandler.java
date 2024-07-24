package com.example.glife.handler;

import com.example.glife.entity.UserBadgeId;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(UserBadgeId.class)
public class UserBadgeIdTypeHandler extends BaseTypeHandler<UserBadgeId> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserBadgeId parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getUserId() + "," + parameter.getBadgeId());
    }

    @Override
    public UserBadgeId getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String[] parts = rs.getString(columnName).split(",");
        return new UserBadgeId(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }

    @Override
    public UserBadgeId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String[] parts = rs.getString(columnIndex).split(",");
        return new UserBadgeId(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }

    @Override
    public UserBadgeId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String[] parts = cs.getString(columnIndex).split(",");
        return new UserBadgeId(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }
}
