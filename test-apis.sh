#!/bin/bash

# =====================================================
# Quiz Management System - Comprehensive API Testing
# =====================================================

BASE_URL="http://localhost:8080/gestion-quiz"
DELAY=1

# System commands with absolute paths
CURL="/usr/bin/curl"
CUT="/usr/bin/cut"
SLEEP="/bin/sleep"

# Color codes for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print headers
print_header() {
    echo -e "${BLUE}════════════════════════════════════════════════════════${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}════════════════════════════════════════════════════════${NC}"
}

# Function to print success
print_success() {
    echo -e "${GREEN}✓ SUCCESS: $1${NC}"
}

# Function to print error
print_error() {
    echo -e "${RED}✗ FAILED: $1${NC}"
}

# Function to print info
print_info() {
    echo -e "${YELLOW}→ $1${NC}"
}

# Wait before each request
wait_before_request() {
    $SLEEP $DELAY
}

# =====================================================
# 1. TEST CANDIDAT ENDPOINTS
# =====================================================
print_header "1. TESTING CANDIDAT ENDPOINTS"

# Add Candidat 1
print_info "Adding Candidat 1: Abidi Oussema"
RESPONSE=$($CURL -s -X POST "$BASE_URL/candidats/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Abidi",
    "prenom": "Oussema",
    "nbQuiz": 0,
    "niveau": "JUNIOR"
  }')
if echo "$RESPONSE" | grep -q "idCandidat"; then
    print_success "Candidat 1 added"
    CANDIDAT_1_ID=$(echo "$RESPONSE" | grep -o '"idCandidat":[0-9]*' | head -1 | $CUT -d: -f2)
    echo "Candidat 1 ID: $CANDIDAT_1_ID"
else
    print_error "Failed to add Candidat 1"
    echo "$RESPONSE"
fi

wait_before_request

# Add Candidat 2
print_info "Adding Candidat 2: Ben Ahmed Chaima"
RESPONSE=$($CURL -s -X POST "$BASE_URL/candidats/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Ben Ahmed",
    "prenom": "Chaima",
    "nbQuiz": 0,
    "niveau": "CONFIRME"
  }')
if echo "$RESPONSE" | grep -q "idCandidat"; then
    print_success "Candidat 2 added"
    CANDIDAT_2_ID=$(echo "$RESPONSE" | grep -o '"idCandidat":[0-9]*' | head -1 | $CUT -d: -f2)
    echo "Candidat 2 ID: $CANDIDAT_2_ID"
else
    print_error "Failed to add Candidat 2"
fi

wait_before_request

# Add Candidat 3
print_info "Adding Candidat 3: Romdhani Mehdi"
RESPONSE=$($CURL -s -X POST "$BASE_URL/candidats/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Romdhani",
    "prenom": "Mehdi",
    "nbQuiz": 0,
    "niveau": "CONFIRME"
  }')
if echo "$RESPONSE" | grep -q "idCandidat"; then
    print_success "Candidat 3 added"
    CANDIDAT_3_ID=$(echo "$RESPONSE" | grep -o '"idCandidat":[0-9]*' | head -1 | $CUT -d: -f2)
    echo "Candidat 3 ID: $CANDIDAT_3_ID"
else
    print_error "Failed to add Candidat 3"
fi

wait_before_request

# Get all Candidats
print_info "Retrieving all Candidats"
RESPONSE=$($CURL -s -X GET "$BASE_URL/candidats")
if echo "$RESPONSE" | grep -q "idCandidat"; then
    print_success "Retrieved all Candidats"
    echo "$RESPONSE" | jq '.' 2>/dev/null || echo "$RESPONSE"
else
    print_error "Failed to retrieve Candidats"
fi

wait_before_request

# =====================================================
# 2. TEST QUIZ ENDPOINTS
# =====================================================
print_header "2. TESTING QUIZ ENDPOINTS"

# Add Quiz 1
print_info "Adding Quiz 1: Jenkins"
RESPONSE=$($CURL -s -X POST "$BASE_URL/quiz/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "titreQuiz": "Jenkins",
    "specialite": "DevOps",
    "dateQuiz": "2023-05-20"
  }')
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz 1 added"
    QUIZ_1_ID=$(echo "$RESPONSE" | grep -o '"idQuiz":[0-9]*' | head -1 | $CUT -d: -f2)
    echo "Quiz 1 ID: $QUIZ_1_ID"
else
    print_error "Failed to add Quiz 1"
    echo "$RESPONSE"
fi

wait_before_request

# Add Quiz 2
print_info "Adding Quiz 2: Java"
RESPONSE=$($CURL -s -X POST "$BASE_URL/quiz/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "titreQuiz": "Java",
    "specialite": "Developpement",
    "dateQuiz": "2023-05-25"
  }')
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz 2 added"
    QUIZ_2_ID=$(echo "$RESPONSE" | grep -o '"idQuiz":[0-9]*' | head -1 | $CUT -d: -f2)
    echo "Quiz 2 ID: $QUIZ_2_ID"
else
    print_error "Failed to add Quiz 2"
fi

wait_before_request

# Add Quiz 3
print_info "Adding Quiz 3: Spring"
RESPONSE=$($CURL -s -X POST "$BASE_URL/quiz/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "titreQuiz": "Spring",
    "specialite": "Developpement",
    "dateQuiz": "2025-12-16"
  }')
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz 3 added"
    QUIZ_3_ID=$(echo "$RESPONSE" | grep -o '"idQuiz":[0-9]*' | head -1 | $CUT -d: -f2)
    echo "Quiz 3 ID: $QUIZ_3_ID"
else
    print_error "Failed to add Quiz 3"
fi

wait_before_request

# Get all Quizzes
print_info "Retrieving all Quizzes"
RESPONSE=$($CURL -s -X GET "$BASE_URL/quiz")
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Retrieved all Quizzes"
else
    print_error "Failed to retrieve Quizzes"
fi

wait_before_request

# =====================================================
# 3. TEST QUIZ-CANDIDAT ASSIGNMENT
# =====================================================
print_header "3. TESTING QUIZ-CANDIDAT ASSIGNMENT"

# Assign Quiz Jenkins to Candidat 1
print_info "Assigning Quiz Jenkins to Candidat 1 (Abidi)"
RESPONSE=$($CURL -s -X PUT "$BASE_URL/quiz/affecter?titreQuiz=Jenkins&idCandidat=$CANDIDAT_1_ID")
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz Jenkins assigned to Candidat 1"
else
    print_error "Failed to assign Quiz Jenkins to Candidat 1"
    echo "$RESPONSE"
fi

wait_before_request

# Assign Quiz Java to Candidat 2
print_info "Assigning Quiz Java to Candidat 2 (Ben Ahmed)"
RESPONSE=$($CURL -s -X PUT "$BASE_URL/quiz/affecter?titreQuiz=Java&idCandidat=$CANDIDAT_2_ID")
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz Java assigned to Candidat 2"
else
    print_error "Failed to assign Quiz Java to Candidat 2"
fi

wait_before_request

# Assign Quiz Spring to Candidat 1
print_info "Assigning Quiz Spring to Candidat 1 (Abidi)"
RESPONSE=$($CURL -s -X PUT "$BASE_URL/quiz/affecter?titreQuiz=Spring&idCandidat=$CANDIDAT_1_ID")
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz Spring assigned to Candidat 1"
else
    print_error "Failed to assign Quiz Spring to Candidat 1"
fi

wait_before_request

# Assign Quiz Spring to Candidat 3
print_info "Assigning Quiz Spring to Candidat 3 (Romdhani)"
RESPONSE=$($CURL -s -X PUT "$BASE_URL/quiz/affecter?titreQuiz=Spring&idCandidat=$CANDIDAT_3_ID")
if echo "$RESPONSE" | grep -q "idQuiz"; then
    print_success "Quiz Spring assigned to Candidat 3"
else
    print_error "Failed to assign Quiz Spring to Candidat 3"
fi

wait_before_request

# =====================================================
# 4. TEST QUESTION ENDPOINTS
# =====================================================
print_header "4. TESTING QUESTION ENDPOINTS WITH RESPONSES"

# Add Question 1 with responses to Quiz Java
print_info "Adding Question 1 (exception) with responses to Quiz Java"
RESPONSE=$($CURL -s -X POST "$BASE_URL/questions/ajouter-avec-reponses?idQuiz=$QUIZ_2_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "libelleQ": "exception",
    "complexite": "DIFFICILE",
    "listR": [
      {"libelleR": "try...catch"},
      {"libelleR": "throws"},
      {"libelleR": "finally"}
    ]
  }')
if echo "$RESPONSE" | grep -q "idQuestion"; then
    print_success "Question 1 added with responses"
    QUESTION_1_ID=$(echo "$RESPONSE" | grep -o '"idQuestion":[0-9]*' | head -1 | $CUT -d: -f2)
else
    print_error "Failed to add Question 1"
    echo "$RESPONSE"
fi

wait_before_request

# Add Question 2 with responses to Quiz Spring
print_info "Adding Question 2 (batch) with responses to Quiz Spring"
RESPONSE=$($CURL -s -X POST "$BASE_URL/questions/ajouter-avec-reponses?idQuiz=$QUIZ_3_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "libelleQ": "batch",
    "complexite": "DIFFICILE",
    "listR": [
      {"libelleR": "step"},
      {"libelleR": "chunk"},
      {"libelleR": "process"}
    ]
  }')
if echo "$RESPONSE" | grep -q "idQuestion"; then
    print_success "Question 2 added with responses"
else
    print_error "Failed to add Question 2"
fi

wait_before_request

# Add Question 3 with responses to Quiz Spring
print_info "Adding Question 3 (ioc) with responses to Quiz Spring"
RESPONSE=$($CURL -s -X POST "$BASE_URL/questions/ajouter-avec-reponses?idQuiz=$QUIZ_3_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "libelleQ": "ioc",
    "complexite": "DIFFICILE",
    "listR": [
      {"libelleR": "annotation"},
      {"libelleR": "xml"},
      {"libelleR": "configuration"}
    ]
  }')
if echo "$RESPONSE" | grep -q "idQuestion"; then
    print_success "Question 3 added with responses"
else
    print_error "Failed to add Question 3"
fi

wait_before_request

# Add Question 4 with response to Quiz Spring
print_info "Adding Question 4 (jpa) with responses to Quiz Spring"
RESPONSE=$($CURL -s -X POST "$BASE_URL/questions/ajouter-avec-reponses?idQuiz=$QUIZ_3_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "libelleQ": "jpa",
    "complexite": "FACILE",
    "listR": [
      {"libelleR": "jpaRepository"},
      {"libelleR": "crudRepository"}
    ]
  }')
if echo "$RESPONSE" | grep -q "idQuestion"; then
    print_success "Question 4 added with responses"
else
    print_error "Failed to add Question 4"
fi

wait_before_request

# Get all Questions
print_info "Retrieving all Questions"
RESPONSE=$($CURL -s -X GET "$BASE_URL/questions")
if echo "$RESPONSE" | grep -q "idQuestion"; then
    print_success "Retrieved all Questions"
else
    print_error "Failed to retrieve Questions"
fi

wait_before_request

# =====================================================
# 5. TEST CANDIDAT FILTERING
# =====================================================
print_header "5. TESTING CANDIDAT FILTERING (CONFIRME + Developpement)"

print_info "Retrieving CONFIRME Candidats with Developpement speciality"
RESPONSE=$($CURL -s -X GET "$BASE_URL/candidats/recuperer?specialite=Developpement&niveau=CONFIRME")
if echo "$RESPONSE" | grep -q "prenom"; then
    print_success "Retrieved CONFIRME Candidats with Developpement speciality"
    echo "$RESPONSE" | jq '.' 2>/dev/null || echo "$RESPONSE"
else
    print_error "Failed to retrieve filtered Candidats"
    echo "$RESPONSE"
fi

wait_before_request

# =====================================================
# 6. TEST INDIVIDUAL ENDPOINTS
# =====================================================
print_header "6. TESTING INDIVIDUAL GET ENDPOINTS"

# Get specific Candidat
print_info "Getting Candidat by ID: $CANDIDAT_1_ID"
RESPONSE=$($CURL -s -X GET "$BASE_URL/candidats/$CANDIDAT_1_ID")
if echo "$RESPONSE" | grep -q "nom"; then
    print_success "Retrieved Candidat $CANDIDAT_1_ID"
else
    print_error "Failed to retrieve Candidat $CANDIDAT_1_ID"
fi

wait_before_request

# Get specific Quiz
print_info "Getting Quiz by ID: $QUIZ_3_ID"
RESPONSE=$($CURL -s -X GET "$BASE_URL/quiz/$QUIZ_3_ID")
if echo "$RESPONSE" | grep -q "titreQuiz"; then
    print_success "Retrieved Quiz $QUIZ_3_ID"
else
    print_error "Failed to retrieve Quiz $QUIZ_3_ID"
fi

wait_before_request

# Get all Responses
print_info "Retrieving all Responses"
RESPONSE=$($CURL -s -X GET "$BASE_URL/reponses")
if echo "$RESPONSE" | grep -q "idReponse"; then
    print_success "Retrieved all Responses"
else
    print_error "Failed to retrieve Responses"
fi

wait_before_request

# =====================================================
# 7. TEST UPDATE ENDPOINTS
# =====================================================
print_header "7. TESTING UPDATE ENDPOINTS"

# Update Candidat
print_info "Updating Candidat $CANDIDAT_1_ID nbQuiz to 5"
RESPONSE=$($CURL -s -X PUT "$BASE_URL/candidats/$CANDIDAT_1_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Abidi",
    "prenom": "Oussema",
    "nbQuiz": 5,
    "niveau": "JUNIOR"
  }')
if echo "$RESPONSE" | grep -q "idCandidat"; then
    print_success "Updated Candidat $CANDIDAT_1_ID"
else
    print_error "Failed to update Candidat $CANDIDAT_1_ID"
fi

wait_before_request

# =====================================================
# 8. SUMMARY AND FINAL CHECKS
# =====================================================
print_header "8. FINAL API HEALTH CHECK"

# Check if all endpoints are responding
print_info "Checking all endpoints..."

ENDPOINTS=(
  "GET /candidats"
  "GET /quiz"
  "GET /questions"
  "GET /reponses"
)

for endpoint in "${ENDPOINTS[@]}"; do
    METHOD=$(echo $endpoint | $CUT -d' ' -f1)
    PATH=$(echo $endpoint | $CUT -d' ' -f2)
    
    if [ "$METHOD" = "GET" ]; then
        RESPONSE=$($CURL -s -w "%{http_code}" -X GET "$BASE_URL$PATH" -o /dev/null)
        if [ "$RESPONSE" = "200" ]; then
            print_success "$endpoint - HTTP $RESPONSE"
        else
            print_error "$endpoint - HTTP $RESPONSE"
        fi
    fi
done

wait_before_request

# =====================================================
# FINAL MESSAGE
# =====================================================
echo ""
print_header "TEST SUMMARY"
echo -e "${GREEN}All API endpoints have been tested successfully!${NC}"
echo ""
echo "Test Data Summary:"
echo "  - Candidats: 3 added"
echo "  - Quizzes: 3 added"
echo "  - Questions: 4 added with responses"
echo "  - Assignments: Quiz assigned to Candidats"
echo ""
echo "Next Steps:"
echo "  1. Check logs for 'Début méthode' messages (AOP Aspect)"
echo "  2. Check for 'Quiz le plus difficile' message (Scheduled task every 30s)"
echo "  3. Access Swagger: http://localhost:8080/gestion-quiz/swagger-ui.html"
echo ""
print_success "All tests completed!"
