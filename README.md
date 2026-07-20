# ChainVerse Sentinel Backend API Documentation (v1.0)

**Base URL**

```
http://localhost:8080/api/v1
```

---

# 1. Analyze Transaction

### Endpoint

```
POST /transactions/analyze
```

### Description

Analyzes a transaction using:

* Behaviour Engine
* Trust Engine
* Fraud Memory Engine
* AI Risk Engine
* Policy Engine

Automatically:

* Calculates Risk Score
* Determines Decision
* Saves Transaction
* Creates Audit Log
* Generates Blockchain Hash

### Request Body

```json
{
  "customerCode": "CUST001",
  "beneficiaryCode": "BEN003",
  "amount": 450000,
  "purpose": "Investment",
  "deviceId": "Unknown Device",
  "location": "Delhi"
}
```

### Response

```json
{
  "success": true,
  "message": "Request processed successfully",
  "timestamp": "...",
  "data": {
    "transactionId": "...",
    "analysisTime": "...",
    "riskScore": 95,
    "riskLevel": "CRITICAL",
    "decision": "BLOCKED",
    "reasons": [
      "High transaction amount",
      "Unknown device",
      "High-risk beneficiary"
    ]
  }
}
```

---

# 2. Get All Transactions

### Endpoint

```
GET /transactions
```

### Description

Returns all analyzed transactions ordered by latest first.

### Response

```json
[
  {
    "transactionId": "TXN000001",
    "customerCode": "CUST001",
    "beneficiaryCode": "BEN001",
    "amount": 2500,
    "riskScore": 5,
    "riskLevel": "LOW",
    "decision": "APPROVED",
    "analysisTime": "2026-07-19T10:15:00"
  }
]
```

---

# 3. Get Transaction Details

### Endpoint

```
GET /transactions/{transactionId}
```

### Description

Returns complete information of a single transaction.

### Path Variable

```
transactionId
```

Example

```
GET /transactions/TXN000003
```

---

# 4. Audit Details

### Endpoint

```
GET /audit/{transactionId}
```

### Description

Returns audit trail information for a transaction.

### Response

```json
{
  "transactionId": "TXN000003",
  "decision": "APPROVED",
  "riskScore": 8,
  "riskLevel": "LOW",
  "auditTime": "...",
  "action": "APPROVED",
  "blockchainHash": "2e7d..."
}
```

---

# 5. Dashboard Summary

### Endpoint

```
GET /dashboard/summary
```

### Description

Returns overall dashboard statistics.

### Response

```json
{
  "totalTransactions": 3,
  "approved": 2,
  "blocked": 1,
  "otpVerification": 0,
  "faceVerification": 0,
  "coolingPeriod": 0,
  "averageRiskScore": 36.0
}
```

---

# 6. Recent Transactions

### Endpoint

```
GET /dashboard/recent-transactions
```

### Description

Returns the latest analyzed transactions for the dashboard.

---

# 7. Risk Distribution

### Endpoint

```
GET /dashboard/risk-distribution
```

### Description

Returns the number of transactions in each risk category.

### Response

```json
{
  "low": 2,
  "medium": 0,
  "high": 0,
  "critical": 1
}
```

---

# 8. Decision Distribution

### Endpoint

```
GET /dashboard/decision-distribution
```

### Description

Returns transaction count grouped by decision.

### Response

```json
{
  "approved": 2,
  "blocked": 1,
  "otpRequired": 0,
  "faceVerification": 0,
  "coolingOff": 0
}
```

---

# 9. Risk Trend

### Endpoint

```
GET /dashboard/risk-trend
```

### Description

Returns transaction risk scores ordered by analysis time.

### Response

```json
[
  {
    "analysisTime": "2026-07-19T11:00:00",
    "riskScore": 8
  },
  {
    "analysisTime": "2026-07-19T10:15:00",
    "riskScore": 5
  }
]
```

---

# 10. Health Check

### Endpoint

```
GET /health
```

### Description

Returns the current backend health status.

### Response

```json
{
  "application": "ChainVerse Sentinel",
  "status": "UP",
  "database": "CONNECTED",
  "blockchain": "ACTIVE",
  "version": "1.0.0"
}
```

---

# HTTP Status Codes

| Code | Description           |
| ---- | --------------------- |
| 200  | Request Successful    |
| 400  | Invalid Request       |
| 404  | Resource Not Found    |
| 500  | Internal Server Error |

---

# Core Features Covered

* Customer Verification
* Beneficiary Verification
* Behaviour Analysis
* Trust Analysis
* Fraud Memory Analysis
* AI Risk Scoring
* Policy-Based Decision Engine
* Transaction History
* Audit Trail
* Blockchain Hash Generation
* Dashboard Analytics
* Health Monitoring
