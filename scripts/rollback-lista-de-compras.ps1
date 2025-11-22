 # scripts/rollback-lista-de-compras.ps1
# Usage: .\scripts\rollback-lista-de-compras.ps1 [-ZipPath <path-to-backup-zip>]
# If ZipPath is omitted the script will try to use the most recent backup matching lista_de_compras_backup_*.zip

param(
    [string]$ZipPath
)

$repoRoot = Split-Path -Parent $PSScriptRoot
$backupDir = Join-Path $repoRoot "target\backup"

if (-not $ZipPath) {
    # Find newest matching backup
    $pattern = Join-Path $backupDir "lista_de_compras_backup_*.zip"
    $cand = Get-ChildItem -Path $backupDir -Filter 'lista_de_compras_backup_*.zip' -File -ErrorAction SilentlyContinue |
            Sort-Object LastWriteTime -Descending | Select-Object -First 1
    if ($null -eq $cand) {
        Write-Host "No backup zip found in $backupDir. You can provide -ZipPath manually."; exit 1
    }
    $ZipPath = $cand.FullName
}

if (-not (Test-Path $ZipPath)) {
    Write-Host "Specified zip not found: $ZipPath"; exit 1
}

Write-Host "Restoring backup from: $ZipPath to repo root: $repoRoot"
try {
    Expand-Archive -Path $ZipPath -DestinationPath $repoRoot -Force
    Write-Host "Restore complete. Files extracted to $repoRoot"
} catch {
    Write-Host "Restore failed: $_"; exit 1
}

Write-Host "After restore, run '.\\mvnw.cmd package' to verify the project builds."
