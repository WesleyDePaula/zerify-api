param(
    [switch]$AutoConfirm
)

# scripts/remove-lista-de-compras.ps1
# Usage: run from PowerShell. This script creates a timestamped backup zip under target\backup
# and removes the listed files after confirmation. Use -AutoConfirm to skip the interactive prompt.

$timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
# Repo root is parent of the scripts directory
$repoRoot = Split-Path -Parent $PSScriptRoot
$backupDir = Join-Path $repoRoot "target\backup"
New-Item -Path $backupDir -ItemType Directory -Force | Out-Null
$backupZip = Join-Path $backupDir "lista_de_compras_backup_$timestamp.zip"

# List the files identified as part of the shopping-list feature (relative to repo root)
$files = @(
    'src\main\java\br\furb\zerify\zerifyapi\schedule\NotificationSchedule.java',
    'src\main\java\br\furb\zerify\zerifyapi\services\NotificationService.java',
    'src\main\java\br\furb\zerify\zerifyapi\services\impl\NotificationServiceImpl.java',
    'src\main\java\br\furb\zerify\zerifyapi\domain\SendEmailInput.java',
    'src\main\java\br\furb\zerify\zerifyapi\domain\alimento\dto\AlimentosAVencerProjection.java',
    'src\main\java\br\furb\zerify\zerifyapi\domain\alimento\AlimentoRepositoryCustom.java',
    'src\main\java\br\furb\zerify\zerifyapi\domain\alimento\AlimentoRepositoryCustomImpl.java'
)

if ($files.Count -eq 0) {
    Write-Host "No files listed for backup/removal. Exiting."; exit 0
}

# Resolve existing files
$existing = @()
foreach ($f in $files) {
    $abs = Join-Path $repoRoot $f
    if (Test-Path $abs) { $existing += $f } else { Write-Host "Not found (skipped): $f" }
}

if ($existing.Count -eq 0) {
    Write-Host "None of the listed files exist on disk. Nothing to do."; exit 0
}

Write-Host "The following files will be backed up and then removed:"
$existing | ForEach-Object { Write-Host " - $_" }

# Create zip backup (run with working directory = repo root so relative paths are stored)
Push-Location $repoRoot
try {
    Write-Host "Creating backup zip: $backupZip"
    Compress-Archive -Path $existing -DestinationPath $backupZip -Force
    Write-Host "Backup created successfully."
} catch {
    Write-Host "Failed to create backup: $_"; Pop-Location; exit 1
} finally {
    Pop-Location
}

# Confirm deletion
if (-not $AutoConfirm) {
    $confirm = Read-Host "Type 'YES' to delete the backed-up files"
} else {
    $confirm = 'YES'
}

if ($confirm -ne 'YES') {
    Write-Host "Aborted by user. Backup is at: $backupZip"; exit 0
}

# Remove files
foreach ($f in $existing) {
    $abs = Join-Path $repoRoot $f
    if (Test-Path $abs) {
        Write-Host "Removing: $f"
        Remove-Item -LiteralPath $abs -Recurse -Force
    } else {
        Write-Host "Already removed/missing: $f"
    }
}

Write-Host "Removal complete. Backup stored at: $backupZip"
Write-Host "Next: run '.\\mvnw.cmd package' (recommended) or 'mvn -f pom.xml package' to verify build."
